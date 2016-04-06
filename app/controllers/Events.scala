package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import play.api.libs.json._
import models.{ Event, TicketBlock }
import controllers.responses._
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._
import akka.util.Timeout
import play.api.Play.current
import scala.concurrent.duration._


object Events extends Controller {
  
  // Request data of all events
  def list = Action.async { request =>
    val events: Future[Seq[Event]] = Event.list
    
    events.map { list => Ok(Json.toJson(SuccessResponse(list)))}
  }
  
  // Request data of a specific event identified by ID
  def getByID(eventID: Long) = Action.async { request =>
    val eventFuture: Future[Option[Event]] = Event.getByID(eventID)
    
    eventFuture.map { event => 
      event.fold{
        NotFound(Json.toJson(ErrorResponse(NOT_FOUND, "No event found")))
      }{ e =>
        Ok(Json.toJson(SuccessResponse(e)))
      }
    }
  }
  
  // Create a new event
  def create = Action.async(parse.json){ request =>
    // Data for new event is received as a Json object
    val incomingBody: JsResult[Event] = request.body.validate[Event]
    
    // If the Json can be validated as represinting an Event, create event, otherwise return an error response
    incomingBody.fold(error => {
      val errorMessage = s"Invalid JSON: ${error}"
      val response = ErrorResponse(1000, errorMessage)
      Future.successful(BadRequest(Json.toJson(response)))
    }, event => {
      val createdEvent: Future[Event] = Event.create(event)
      
      createdEvent map { crEv =>
        Created(Json.toJson(SuccessResponse(crEv)))}
    })
  }
  
  // Get data of all ticket blocks for a specific event
  def ticketBlockForEvent(eventID: Long) = Action.async { request =>
   
    val eventF = Event.getByID(eventID)
    
    eventF.flatMap { event =>  
      event.fold { 
        Future.successful(NotFound(Json.toJson(ErrorResponse(NOT_FOUND, "No event found")))) 
        } { e =>
        val timeoutKey = "ticketoverlords.timeout.ticket_availability_ms"
        val configuredTimeout = current.configuration.getInt(timeoutKey)
        val resolvedTimeout = configuredTimeout.getOrElse(400)
        implicit val timeout = Timeout(resolvedTimeout.milliseconds)
        // Requests available ticket blocks for this event
        val ticketBlocks: Future[Seq[TicketBlock]] =
          e.ticketBlocksWithAvailability
        ticketBlocks map { tb =>
          Ok(Json.toJson(SuccessResponse(tb)))
        }
      }
    }
  }
}