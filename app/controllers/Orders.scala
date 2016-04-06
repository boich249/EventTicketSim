package controllers
import play.api.mvc._
import javax.inject._
import play.api.libs.json.Json
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.concurrent.Akka
import play.api.Play.current
import akka.actor.Props
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import play.api.Logger

import models.{ Order, TicketBlock, TicketIssuer, InsufficientTicketsAvailable, TicketBlockUnavailable}
import controllers.responses._

object Orders extends Controller{
  
  val issuer = TicketIssuer.getSelection              // Akka Actor used to place orders concurrently
  
  // Get data of all orders
  def list = Action.async { request =>
    Order.list map { o => Ok(Json.toJson(SuccessResponse(o))) }
  }
  
  // Request data of a specific order identified by ID
  def getByID(orderID: Long) = Action.async { request =>
    Order getByID(orderID) map {
      case Some(o) => Ok(Json.toJson(SuccessResponse(o)))
      case _ => NotFound(Json.toJson(ErrorResponse(NOT_FOUND, "No order found")))
    }
  }
  
  // Create a new order
  def create = Action.async(parse.json) { request =>
    // Validate incoming json
    request.body.validate[Order] fold(error => {
      // Error response if doesn't validate
      val response = ErrorResponse(ErrorResponse.INVALID_JSON, s"Invalid JSON: ${error}")
      Future.successful(BadRequest(Json.toJson(response)))
    }, // Once Json validates:
    { order =>
      implicit val timeout = Timeout(DurationInt(5).seconds)
      //Request Actor to place an order and return it's data
      val orderFuture = (issuer ? order).mapTo[Order]
      
      orderFuture map { createdOrder =>
        // If order was successful
        Ok(Json.toJson(SuccessResponse(createdOrder)))
      } recover({
        // Otherwise return an informative error response
        case ita: InsufficientTicketsAvailable => {
          val responseMessage =  "There are not enough tickets remaining to complete this order." +
            s" Quantity Remaining: ${ita.ticketsAvailable}"
          val response = ErrorResponse(ErrorResponse.NOT_ENOUGH_TICKETS, responseMessage)
          
          BadRequest(Json.toJson(response))
        }
        case tba: TicketBlockUnavailable => {
          val responseMsg = 
            s"Ticket Block ${order.ticketBlockID} is not available."
          val response = ErrorResponse(
              ErrorResponse.TICKET_BLOCK_UNAVAILABLE,
              responseMsg)
          BadRequest(Json.toJson(response))
        }
        case unexpected => {
          Logger.error(
          s"Unexpected error while placing an order: ${unexpected.toString}")
          val response = ErrorResponse(
            INTERNAL_SERVER_ERROR,
            "An unexpected error occurred")

          InternalServerError(Json.toJson(response))
        }
      }) 
    })
  }
  
}