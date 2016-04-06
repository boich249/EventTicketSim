package models

import akka.actor.Actor
import akka.actor.Status.{ Failure => ActorFailure }
import play.api.libs.concurrent.Execution.Implicits._
import scala.concurrent.Future
import akka.actor.{ ActorRef, Props }
import play.api.libs.concurrent.Akka
import play.api.Play.current
import play.api.Logger

// Serves as a middle man between askers and the specific issuers for each ticket block
class TicketIssuer extends Actor {
  
  var workers = Map[Long, ActorRef]()
  
  // Creates worker Actors. Checks if worker for specified ticket block exists, otherwise creates one
  def createWorker(ticketBlockID: Long){
    if(!workers.contains(ticketBlockID)){
      val worker = context.actorOf(
          Props(classOf[TicketIssuerWorker], ticketBlockID),
          name = ticketBlockID.toString)
      workers = workers + (ticketBlockID -> worker)
    }
  }
  
  // Places orders received by the receive method 
  def placeOrder(order: Order){
    val workerRef = workers.get(order.ticketBlockID)
    
    workerRef.fold {
      sender ! ActorFailure(TicketBlockUnavailable(order.ticketBlockID))
    } { worker =>
      // Forward to individual ticket block issuers
      worker forward order
    }
  }
  
  // Check availability of a ticket block 
  def checkAvailability(message: AvailabilityCheck){
    val workerRef = workers.get(message.ticketBlockID)
    workerRef.fold {
      sender ! ActorFailure(TicketBlockUnavailable(message.ticketBlockID))
    } { worker =>
      worker forward message
    }
  }
 
  // Possible requests from this Actor
  def receive = {
    case order: Order         => placeOrder(order)
    case a: AvailabilityCheck => checkAvailability(a)
    case NewTicketBlock(t)    => t.id foreach createWorker
  }
 
  // Setup
  override def preStart = {
    val ticketBlockResult = TicketBlock.list
    
    for {
      ticketBlocks <- ticketBlockResult
      block <- ticketBlocks
      id <- block.id
    } createWorker(id)
  }
}

// Companion object
object TicketIssuer {
  def props = Props[TicketIssuer]
  
  private val reference = Akka.system.actorOf(
      TicketIssuer.props,
      name = "ticketIssuer")
      
  def getSelection = Akka.system.actorSelection("/user/ticketIssuer")
}

// case class used to request number of available tickets
case class AvailabilityCheck(ticketBlockID: Long)

// case class used to inform ticket issuer of new ticket blocks available 
case class NewTicketBlock(ticketBlock: TicketBlock)

// Exception class for unreachable ticket block Actors
case class TicketBlockUnavailable(ticketBlockID: Long) extends Throwable

