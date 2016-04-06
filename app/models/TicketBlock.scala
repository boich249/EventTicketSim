package models

import play.api.Logger
import org.joda.time.DateTime
import play.api.libs.json.{Json, Format}

import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import play.api.Play.current
import play.api.db.DBApi
import SlickMapping.jodaDateTimeMapping
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._
import akka.actor.ActorSelection
import play.libs.Akka

// Case class representing a TicketBlock Entity 
case class TicketBlock(
    id: Option[Long],
    eventID: Long,
    name: String,
    productCode: String,
    price: BigDecimal,
    initialSize: Int,
    saleStart: DateTime,
    saleEnd: DateTime,
    availability: Option[Int] = None) // availability not part of TicketBlock entity

// Companion object to the TicketBlock case class
object TicketBlock{
  implicit val format: Format[TicketBlock] = Json.format[TicketBlock]
  
  
  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](current)
  import dbConfig._
  import dbConfig.driver.api._
  
  // Slick access to TicketBlocks table
  class TicketBlocksTable(tag: Tag) extends Table[TicketBlock](tag, "TICKET_BLOCKS"){
    
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def eventID = column[Long]("EVENT_ID")
    def name = column[String]("NAME")
    def productCode = column[String]("PRODUCT_CODE")
    def price = column[BigDecimal]("PRICE")
    def initialSize = column[Int]("INITIAL_SIZE")
    def saleStart = column[DateTime]("SALE_START")
    def saleEnd = column[DateTime]("SALE_END")
    
    def event = foreignKey("TB_EVENT", eventID, Event.table)(_.id)
    
    def * = (id.?, eventID, name, productCode, price, initialSize, saleStart, saleEnd)<>(
        (TicketBlock.apply(_: Option[Long], _: Long, _: String, _: String, _: BigDecimal, _: Int, _: DateTime, _: DateTime,None)).tupled,
        { tb: TicketBlock =>
          TicketBlock.unapply(tb).map {
            case (a, b, c, d, e, f, g, h, _) => (a, b, c, d, e, f, g, h)
          }
        }
      )
  }
  
  val table = TableQuery[TicketBlocksTable]
  
  // Get all TicketBlock tuples from DB
  def list: Future[Seq[TicketBlock]] = {
    val blockList = table.result
    db.run(blockList)
  }
  
  // Get a specific TicketBlock from the DB
  def getByID(blockID: Long): Future[Option[TicketBlock]] = {
    val blockByID = table.filter{ tb => tb.id === blockID }.result.headOption
    db.run(blockByID)
  }
  
  // Add a new TicketBlock to the DB
  def create(newTicketBlock: TicketBlock): Future[TicketBlock] = {
    val insertion = ( table returning table.map(_.id)) += newTicketBlock
    
    db.run(insertion).map { newID => 
      val createdBlock = newTicketBlock.copy(id = Option(newID)) 
      
      val issuer: ActorSelection = TicketIssuer.getSelection
      issuer ! NewTicketBlock(createdBlock)
      
      createdBlock
    }
  }
  
  // Returns the number of available tickets of a specific TicketBlock as a Future 
  def availability(ticketBlockID: Long): Future[Int] = {
    // Query will result in all ordered tickets
    val orders = for {
      o <- Order.table if o.ticketBlockID === ticketBlockID
    }yield o.ticketQuantity
    
    // Query initial ticket size and remove already ordered tickets
    val quantityLeft = table.filter { 
      _.id === ticketBlockID 
    } map {
        tb => 
          tb.initialSize - orders.sum
    }
    val queryResult = db.run(quantityLeft.result.headOption)

    queryResult.map { _.flatten.getOrElse(0)}
  }
  
  // Get all ticket blocks for a specific event from the DB
  def listForEvent(eventID: Long): Future[Seq[TicketBlock]] = {
    val blockListQ = table.filter { tb =>  
      tb.eventID === eventID 
    }.result
    db.run(blockListQ)
  }
}