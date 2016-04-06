package models

import play.api.Logger
import scala.concurrent.Future
import play.api.libs.json.{Json, Format}
import org.joda.time.DateTime
import models.SlickMapping.jodaDateTimeMapping
import play.api.libs.concurrent.Execution.Implicits._
import utility.OptimisticFuture
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import play.api.Play.current
import play.api.db.DBApi
import akka.util.Timeout
import akka.pattern.ask

// Case class representing an Event Entity 
case class Event(
    id: Option[Long],
    name: String,
    start: DateTime,
    end: DateTime,
    address: String,
    city: String,
    state: String,
    country: String){
  
  
  // Get all available ticket block info associated with this event
  def ticketBlocksWithAvailability(implicit timeout: Timeout): Future[Seq[TicketBlock]] = {
    this.id.fold {
     Future.successful(Nil: Seq[TicketBlock])
    } { eid =>
      // Get all blocks for this event as Future[Seq[TicketBlock]]
      val basicBlocks = TicketBlock.listForEvent(eid)
      val issuer = TicketIssuer.getSelection
      
      val blocksWithAvailability: Future[Seq[TicketBlock]] = 
        // Get the Seq from Future[Seq]
        basicBlocks.flatMap { blocks =>
          
          val updatedBlocks: Seq[Future[TicketBlock]] = for {
            block <- blocks // each ticket block in Seq
            blockID <- block.id
            
            // Get availability and map to Int
            availability = (issuer ? AvailabilityCheck(blockID)).mapTo[Int]
            // Add the availability info to the TB
            updatedBlock = availability.map { a =>  
            block.copy(availability = Option(a))
            }
          } yield updatedBlock
                      
          OptimisticFuture.sequence(updatedBlocks)
        }
      
      blocksWithAvailability
    }
  }
}
    
// Companion object to the Event case class
object Event{
  implicit val format: Format[Event] = Json.format[Event]
  
  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](current)
  import dbConfig._
  import dbConfig.driver.api._
  
  // Slick access to Events table
  class EventsTable(tag: Tag) extends Table[Event](tag, "EVENTS"){
    
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def start = column[DateTime]("START")
    def end = column[DateTime]("END")
    def address = column[String]("ADDRESS")
    def city = column[String]("CITY")
    def state = column[String]("STATE")
    def country = column[String]("COUNTRY")

    def * = (id.?, name, start, end, address, city, state, country) <>
      ((Event.apply _).tupled, Event.unapply)
  }
  
  val table = TableQuery[EventsTable]
  
  // Get all Event tuples from DB
  def list: Future[Seq[Event]] = {
    val eventList = table.result
    db.run(eventList)
  }
  
  // Get a specific Event from the DB
  def getByID(eventID: Long): Future[Option[Event]] = {
    val eventByID = table.filter { ev => ev.id === eventID }.result.headOption
    db.run(eventByID)
  }
  
  // Add a new event to the DB
  def create(newEvent: Event): Future[Event] = {
    val insertion = (table returning table.map(_.id)) += newEvent
    val insertedID: Future[Long] = db.run(insertion)
    
    insertedID.map{ resultID => newEvent.copy(id = Option(resultID))}
  }
}