
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Asher/ticket-overlords/conf/routes
// @DATE:Mon Apr 04 01:52:40 EDT 2016

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseTicketBlocks TicketBlocks = new controllers.ReverseTicketBlocks(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseEvents Events = new controllers.ReverseEvents(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseApplication Application = new controllers.ReverseApplication(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseTickets Tickets = new controllers.ReverseTickets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseOrders Orders = new controllers.ReverseOrders(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseTicketBlocks TicketBlocks = new controllers.javascript.ReverseTicketBlocks(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseEvents Events = new controllers.javascript.ReverseEvents(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseApplication Application = new controllers.javascript.ReverseApplication(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseTickets Tickets = new controllers.javascript.ReverseTickets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseOrders Orders = new controllers.javascript.ReverseOrders(RoutesPrefix.byNamePrefix());
  }

}
