
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Asher/ticket-overlords/conf/routes
// @DATE:Mon Apr 04 01:52:40 EDT 2016

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:6
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:29
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:29
    def at: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.at",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:15
  class ReverseTicketBlocks(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:16
    def list: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.TicketBlocks.list",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tickets/blocks/"})
        }
      """
    )
  
    // @LINE:15
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.TicketBlocks.create",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "tickets/blocks/"})
        }
      """
    )
  
    // @LINE:17
    def getByID: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.TicketBlocks.getByID",
      """
        function(blockID0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tickets/blocks/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("blockID", blockID0) + "/"})
        }
      """
    )
  
  }

  // @LINE:10
  class ReverseEvents(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def list: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Events.list",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "events/"})
        }
      """
    )
  
    // @LINE:10
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Events.create",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "events/"})
        }
      """
    )
  
    // @LINE:12
    def getByID: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Events.getByID",
      """
        function(eventID0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "events/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("eventID", eventID0) + "/"})
        }
      """
    )
  
    // @LINE:13
    def ticketBlockForEvent: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Events.ticketBlockForEvent",
      """
        function(eventID0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "events/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("eventID", eventID0) + "/tickets/blocks/"})
        }
      """
    )
  
  }

  // @LINE:6
  class ReverseApplication(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:31
    def jsRoutes: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.jsRoutes",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "jsr/"})
        }
      """
    )
  
  }

  // @LINE:7
  class ReverseTickets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def ticketsAvailable: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Tickets.ticketsAvailable",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tickets/available"})
        }
      """
    )
  
  }

  // @LINE:20
  class ReverseOrders(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:21
    def list: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Orders.list",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "orders/"})
        }
      """
    )
  
    // @LINE:20
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Orders.create",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "orders/"})
        }
      """
    )
  
    // @LINE:22
    def getByID: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Orders.getByID",
      """
        function(orderID0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "orders/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("orderID", orderID0) + "/"})
        }
      """
    )
  
  }


}
