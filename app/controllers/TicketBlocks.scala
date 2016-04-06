package controllers

import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._

import models.TicketBlock
import controllers.responses._

object TicketBlocks extends Controller {
  
  // Get data of all ticket blocks
  def list = Action.async { response =>
    TicketBlock.list.map { list => 
    Ok(Json.toJson(SuccessResponse(list)))}
  }
  
  // Request data of a specific ticket block identified by ID
  def getByID(blockID: Long) = Action.async { request =>
    val ticketBlock: Future[Option[TicketBlock]] = TicketBlock.getByID(blockID)
   
    ticketBlock.map { tbOp => 
      tbOp.fold{
          NotFound(Json.toJson(ErrorResponse(NOT_FOUND, "No ticket block found")))
      } { tb => 
        Ok(Json.toJson(SuccessResponse(tb)))
        }
    }
  }
  
  // Create a new ticket block
  def create = Action.async(parse.json) { request =>
    val incomingBody: JsResult[TicketBlock] =  request.body.validate[TicketBlock]
    
    incomingBody.fold(error => {
      val errorMessage = s"Invalid JSON: ${error}"
      val response = ErrorResponse(1000, errorMessage)
      Future.successful(BadRequest(Json.toJson(response)))
    }, ticketBlock => {
      val createdBlock: Future[TicketBlock] = TicketBlock.create(ticketBlock)
      createdBlock.map { block => Created(Json.toJson(SuccessResponse(block))) }  
    })
  }
  
  
}