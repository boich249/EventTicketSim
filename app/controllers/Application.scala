package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.routing.JavaScriptReverseRouter


object Application extends Controller{
  //Renders Homepage
  def index = Action{
    Ok(views.html.index())
  }
  
  // Creates JS API endpoints 
  def jsRoutes = Action{ implicit request =>
    Ok(
        JavaScriptReverseRouter("jsRoutes")(
            routes.javascript.Events.list,
            routes.javascript.Events.ticketBlockForEvent,
            routes.javascript.Orders.create
        )
     )
  }
}
