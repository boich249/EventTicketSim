
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Asher/ticket-overlords/conf/routes
// @DATE:Mon Apr 04 01:52:40 EDT 2016

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset

object Routes extends Routes

class Routes extends GeneratedRouter {

  import ReverseRouteContext.empty

  override val errorHandler: play.api.http.HttpErrorHandler = play.api.http.LazyHttpErrorHandler

  private var _prefix = "/"

  def withPrefix(prefix: String): Routes = {
    _prefix = prefix
    router.RoutesPrefix.setPrefix(prefix)
    
    this
  }

  def prefix: String = _prefix

  lazy val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation: Seq[(String, String, String)] = List(
    ("""GET""", prefix, """controllers.Application.index"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tickets/available""", """controllers.Tickets.ticketsAvailable"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """events/""", """controllers.Events.create"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """events/""", """controllers.Events.list"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """events/""" + "$" + """eventID<[^/]+>/""", """controllers.Events.getByID(eventID:Long)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """events/""" + "$" + """eventID<[^/]+>/tickets/blocks/""", """controllers.Events.ticketBlockForEvent(eventID:Long)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tickets/blocks/""", """controllers.TicketBlocks.create"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tickets/blocks/""", """controllers.TicketBlocks.list"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tickets/blocks/""" + "$" + """blockID<[^/]+>/""", """controllers.TicketBlocks.getByID(blockID:Long)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """orders/""", """controllers.Orders.create"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """orders/""", """controllers.Orders.list"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """orders/""" + "$" + """orderID<[^/]+>/""", """controllers.Orders.getByID(orderID:Long)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.at(path:String = "/public", file:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """jsr/""", """controllers.Application.jsRoutes"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_Application_index0_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_Application_index0_invoker = createInvoker(
    controllers.Application.index,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "index",
      Nil,
      "GET",
      """ An example controller showing a sample home page""",
      this.prefix + """"""
    )
  )

  // @LINE:7
  private[this] lazy val controllers_Tickets_ticketsAvailable1_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tickets/available")))
  )
  private[this] lazy val controllers_Tickets_ticketsAvailable1_invoker = createInvoker(
    controllers.Tickets.ticketsAvailable,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Tickets",
      "ticketsAvailable",
      Nil,
      "GET",
      """""",
      this.prefix + """tickets/available"""
    )
  )

  // @LINE:10
  private[this] lazy val controllers_Events_create2_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("events/")))
  )
  private[this] lazy val controllers_Events_create2_invoker = createInvoker(
    controllers.Events.create,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Events",
      "create",
      Nil,
      "POST",
      """ Event Resources""",
      this.prefix + """events/"""
    )
  )

  // @LINE:11
  private[this] lazy val controllers_Events_list3_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("events/")))
  )
  private[this] lazy val controllers_Events_list3_invoker = createInvoker(
    controllers.Events.list,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Events",
      "list",
      Nil,
      "GET",
      """""",
      this.prefix + """events/"""
    )
  )

  // @LINE:12
  private[this] lazy val controllers_Events_getByID4_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("events/"), DynamicPart("eventID", """[^/]+""",true), StaticPart("/")))
  )
  private[this] lazy val controllers_Events_getByID4_invoker = createInvoker(
    controllers.Events.getByID(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Events",
      "getByID",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """events/""" + "$" + """eventID<[^/]+>/"""
    )
  )

  // @LINE:13
  private[this] lazy val controllers_Events_ticketBlockForEvent5_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("events/"), DynamicPart("eventID", """[^/]+""",true), StaticPart("/tickets/blocks/")))
  )
  private[this] lazy val controllers_Events_ticketBlockForEvent5_invoker = createInvoker(
    controllers.Events.ticketBlockForEvent(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Events",
      "ticketBlockForEvent",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """events/""" + "$" + """eventID<[^/]+>/tickets/blocks/"""
    )
  )

  // @LINE:15
  private[this] lazy val controllers_TicketBlocks_create6_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tickets/blocks/")))
  )
  private[this] lazy val controllers_TicketBlocks_create6_invoker = createInvoker(
    controllers.TicketBlocks.create,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.TicketBlocks",
      "create",
      Nil,
      "POST",
      """ Block Resources""",
      this.prefix + """tickets/blocks/"""
    )
  )

  // @LINE:16
  private[this] lazy val controllers_TicketBlocks_list7_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tickets/blocks/")))
  )
  private[this] lazy val controllers_TicketBlocks_list7_invoker = createInvoker(
    controllers.TicketBlocks.list,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.TicketBlocks",
      "list",
      Nil,
      "GET",
      """""",
      this.prefix + """tickets/blocks/"""
    )
  )

  // @LINE:17
  private[this] lazy val controllers_TicketBlocks_getByID8_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tickets/blocks/"), DynamicPart("blockID", """[^/]+""",true), StaticPart("/")))
  )
  private[this] lazy val controllers_TicketBlocks_getByID8_invoker = createInvoker(
    controllers.TicketBlocks.getByID(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.TicketBlocks",
      "getByID",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """tickets/blocks/""" + "$" + """blockID<[^/]+>/"""
    )
  )

  // @LINE:20
  private[this] lazy val controllers_Orders_create9_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("orders/")))
  )
  private[this] lazy val controllers_Orders_create9_invoker = createInvoker(
    controllers.Orders.create,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Orders",
      "create",
      Nil,
      "POST",
      """ Order Resource""",
      this.prefix + """orders/"""
    )
  )

  // @LINE:21
  private[this] lazy val controllers_Orders_list10_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("orders/")))
  )
  private[this] lazy val controllers_Orders_list10_invoker = createInvoker(
    controllers.Orders.list,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Orders",
      "list",
      Nil,
      "GET",
      """""",
      this.prefix + """orders/"""
    )
  )

  // @LINE:22
  private[this] lazy val controllers_Orders_getByID11_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("orders/"), DynamicPart("orderID", """[^/]+""",true), StaticPart("/")))
  )
  private[this] lazy val controllers_Orders_getByID11_invoker = createInvoker(
    controllers.Orders.getByID(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Orders",
      "getByID",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """orders/""" + "$" + """orderID<[^/]+>/"""
    )
  )

  // @LINE:29
  private[this] lazy val controllers_Assets_at12_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_at12_invoker = createInvoker(
    controllers.Assets.at(fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      """ Map static resources from the /public folder to the /assets URL path""",
      this.prefix + """assets/""" + "$" + """file<.+>"""
    )
  )

  // @LINE:31
  private[this] lazy val controllers_Application_jsRoutes13_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("jsr/")))
  )
  private[this] lazy val controllers_Application_jsRoutes13_invoker = createInvoker(
    controllers.Application.jsRoutes,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "jsRoutes",
      Nil,
      "GET",
      """""",
      this.prefix + """jsr/"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_Application_index0_route(params) =>
      call { 
        controllers_Application_index0_invoker.call(controllers.Application.index)
      }
  
    // @LINE:7
    case controllers_Tickets_ticketsAvailable1_route(params) =>
      call { 
        controllers_Tickets_ticketsAvailable1_invoker.call(controllers.Tickets.ticketsAvailable)
      }
  
    // @LINE:10
    case controllers_Events_create2_route(params) =>
      call { 
        controllers_Events_create2_invoker.call(controllers.Events.create)
      }
  
    // @LINE:11
    case controllers_Events_list3_route(params) =>
      call { 
        controllers_Events_list3_invoker.call(controllers.Events.list)
      }
  
    // @LINE:12
    case controllers_Events_getByID4_route(params) =>
      call(params.fromPath[Long]("eventID", None)) { (eventID) =>
        controllers_Events_getByID4_invoker.call(controllers.Events.getByID(eventID))
      }
  
    // @LINE:13
    case controllers_Events_ticketBlockForEvent5_route(params) =>
      call(params.fromPath[Long]("eventID", None)) { (eventID) =>
        controllers_Events_ticketBlockForEvent5_invoker.call(controllers.Events.ticketBlockForEvent(eventID))
      }
  
    // @LINE:15
    case controllers_TicketBlocks_create6_route(params) =>
      call { 
        controllers_TicketBlocks_create6_invoker.call(controllers.TicketBlocks.create)
      }
  
    // @LINE:16
    case controllers_TicketBlocks_list7_route(params) =>
      call { 
        controllers_TicketBlocks_list7_invoker.call(controllers.TicketBlocks.list)
      }
  
    // @LINE:17
    case controllers_TicketBlocks_getByID8_route(params) =>
      call(params.fromPath[Long]("blockID", None)) { (blockID) =>
        controllers_TicketBlocks_getByID8_invoker.call(controllers.TicketBlocks.getByID(blockID))
      }
  
    // @LINE:20
    case controllers_Orders_create9_route(params) =>
      call { 
        controllers_Orders_create9_invoker.call(controllers.Orders.create)
      }
  
    // @LINE:21
    case controllers_Orders_list10_route(params) =>
      call { 
        controllers_Orders_list10_invoker.call(controllers.Orders.list)
      }
  
    // @LINE:22
    case controllers_Orders_getByID11_route(params) =>
      call(params.fromPath[Long]("orderID", None)) { (orderID) =>
        controllers_Orders_getByID11_invoker.call(controllers.Orders.getByID(orderID))
      }
  
    // @LINE:29
    case controllers_Assets_at12_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at12_invoker.call(controllers.Assets.at(path, file))
      }
  
    // @LINE:31
    case controllers_Application_jsRoutes13_route(params) =>
      call { 
        controllers_Application_jsRoutes13_invoker.call(controllers.Application.jsRoutes)
      }
  }
}
