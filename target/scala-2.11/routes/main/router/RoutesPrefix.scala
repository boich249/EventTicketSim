
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Asher/ticket-overlords/conf/routes
// @DATE:Mon Apr 04 01:52:40 EDT 2016


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
