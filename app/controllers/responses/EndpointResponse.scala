package controllers.responses

import play.api.libs.json.{ JsValue, JsNull, Json, Format, Writes }


// Contains the error information as a response to a failed request
case class ErrorResult(status: Int, message: String)

object ErrorResult{
  implicit val format: Format[ErrorResult] = Json.format[ErrorResult]
}

// Wraps requested data in a uniform response format
case class EndpointResponse(
    result: String,
    response: JsValue,
    error: Option[ErrorResult])
    
object EndpointResponse {
  implicit val format: Format[EndpointResponse] = Json.format[EndpointResponse]

}

// Convenience method used to create error endpoint responses
object ErrorResponse {
  def apply(status: Int, message: String) = EndpointResponse("ko", JsNull, Option(ErrorResult(status, message)))
  
  // Custom error status
  val INVALID_JSON = 1000
  val NOT_ENOUGH_TICKETS = 1001
  val TICKET_BLOCK_UNAVAILABLE = 1002
}

// Convenience class used to create successful endpoint responses
object SuccessResponse {
  def apply[A](successResponse: A)(implicit w: Writes[A]) = EndpointResponse("ok", Json.toJson(successResponse), None)
}