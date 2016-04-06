package models

import play.api.Logger
import akka.actor.Actor
import akka.actor.Status.{ Failure => ActorFailure }
import play.api.libs.concurrent.Execution.Implicits._

// Individual ticket issuers for each ticket block
class TicketIssuerWorker(ticketBlockID: Long) extends Actor {
  
  // Create an order asynchronously
  def placeOrder(order: Order, availability: Int){
    val origin = sender
    
    if(validateRouting(order.ticketBlockID)){
      if(availability >= order.ticketQuantity){
        val newAvailability = availability - order.ticketQuantity
        context.become(normalOperation(newAvailability))
        
        val createdOrder = Order.create(order)
        
        createdOrder map (origin ! _)
        
      } else {
        val failureResponse = InsufficientTicketsAvailable(order.ticketBlockID, availability)
        
        origin ! ActorFailure(failureResponse)
      }
    }
  }
  
  // Make sure this is the Actor responsible for the requested ticket block, otherwise return a Failure
  def validateRouting(requestedID: Long) = {
    if(ticketBlockID != requestedID){
      val msg = s"IssuerWorker #${ticketBlockID} received " +
        s"an order for Ticket Block ${requestedID}"
      
      sender ! ActorFailure(new OrderRoutingException(msg))
      false
    } else true
  }
  
  // Used to tell issuers to add to the number of available tickets
  case class AddTickets(quantity: Int)
  
  // The following methods define possible Receive states
  // and responses to messages according to each state
  // 3 possible states are: initializing, normalOperation, and soldOut
  
  def initializing: Actor.Receive = {
    case AddTickets(availability) => {
      context.become(normalOperation(availability))
    }
    case order: Order => {
      if(validateRouting(order.ticketBlockID)){
        val failureResponse = TicketBlockUnavailable(order.ticketBlockID)
        
        sender ! ActorFailure(failureResponse)
      }
    }
    case AvailabilityCheck(ticketBlockID) => {
      val failureResponse = TicketBlockUnavailable(ticketBlockID)
      sender ! ActorFailure(failureResponse)
    }
  }
  
  def normalOperation(availability: Int): Actor.Receive = {
    case AddTickets(newQuantity)  => context.become(normalOperation(availability + newQuantity))
    case order: Order             => placeOrder(order, availability)
    case _: AvailabilityCheck     => sender ! availability
  }
  
  def soldOut: Actor.Receive = {
    case AddTickets(availability) => {
      context.become(normalOperation(availability))
    }
    case order: Order => {
      if(validateRouting(order.ticketBlockID)){
        val failureResponse = InsufficientTicketsAvailable(order.ticketBlockID, 0)
        
        sender ! ActorFailure(failureResponse)
      }
    }
    case _: AvailabilityCheck => sender ! 0
  }
  
  // Set initial receive state
  def receive = initializing
  
  // Setup by making the correct number of tickets available
  override def preStart {
    val availabilityFuture = TicketBlock.availability(ticketBlockID)
    
    availabilityFuture.onSuccess {
      case result => self ! AddTickets(result)
    }
  }
}

// Exception class for insufficient orders
case class InsufficientTicketsAvailable(
    ticketBlockID: Long,
    ticketsAvailable: Int) extends Throwable

// Exception class for Orders routed to the wrong Actor
class OrderRoutingException(message: String) extends Exception(message)
