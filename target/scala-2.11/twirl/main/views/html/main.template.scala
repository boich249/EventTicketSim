
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object main_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template is called from the `index` template. This template
 * handles the rendering of the page header and body tags. It takes
 * two arguments, a `String` for the title of the page and an `Html`
 * object to insert into the body of the page.
 */
  def apply/*7.2*/(title: String, requireJsModule: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*7.57*/("""

"""),format.raw/*9.1*/("""<!DOCTYPE html>
<html lang="en">
    <head>
        """),format.raw/*12.62*/("""
        """),format.raw/*13.9*/("""<title>"""),_display_(/*13.17*/title),format.raw/*13.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*14.54*/routes/*14.60*/.Assets.at("stylesheets/main.css")),format.raw/*14.94*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*15.59*/routes/*15.65*/.Assets.at("images/favicon.png")),format.raw/*15.97*/("""">
    	<script type="text/javascript" src=""""),_display_(/*16.43*/routes/*16.49*/.Application.jsRoutes),format.raw/*16.70*/(""""></script>
    	"""),format.raw/*19.8*/("""
    """),format.raw/*20.5*/("""<script data-main=""""),_display_(/*20.25*/routes/*20.31*/.Assets.at("javascripts/index")),format.raw/*20.62*/("""" src=""""),_display_(/*20.70*/routes/*20.76*/.Assets.at("lib/requirejs/require.js")),format.raw/*20.114*/(""""></script>
    	
    </head>
    <body>
        """),format.raw/*25.32*/("""
        """),_display_(/*26.10*/content),format.raw/*26.17*/("""
    """),format.raw/*27.5*/("""</body>
</html>
"""))
      }
    }
  }

  def render(title:String,requireJsModule:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title,requireJsModule)(content)

  def f:((String,String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title,requireJsModule) => (content) => apply(title,requireJsModule)(content)

  def ref: this.type = this

}


}

/*
 * This template is called from the `index` template. This template
 * handles the rendering of the page header and body tags. It takes
 * two arguments, a `String` for the title of the page and an `Html`
 * object to insert into the body of the page.
 */
object main extends main_Scope0.main
              /*
                  -- GENERATED --
                  DATE: Mon Apr 04 02:12:37 EDT 2016
                  SOURCE: C:/Users/Asher/ticket-overlords/app/views/main.scala.html
                  HASH: 1e3d54dd49ab2aef3dbc261f8a293980fc37bc09
                  MATRIX: 791->260|941->315|969->317|1049->422|1085->431|1120->439|1146->444|1235->506|1250->512|1305->546|1393->607|1408->613|1461->645|1533->690|1548->696|1590->717|1634->895|1666->900|1713->920|1728->926|1780->957|1815->965|1830->971|1890->1009|1967->1148|2004->1158|2032->1165|2064->1170
                  LINES: 25->7|30->7|32->9|35->12|36->13|36->13|36->13|37->14|37->14|37->14|38->15|38->15|38->15|39->16|39->16|39->16|40->19|41->20|41->20|41->20|41->20|41->20|41->20|41->20|45->25|46->26|46->26|47->27
                  -- GENERATED --
              */
          