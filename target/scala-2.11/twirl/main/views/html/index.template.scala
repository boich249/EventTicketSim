
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object index_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template takes a single argument, a String containing a
 * message to display.
 */
  def apply/*5.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.4*/("""

"""),format.raw/*11.4*/("""
"""),_display_(/*12.2*/main("Ticket Overlords", "main")/*12.34*/ {_display_(Seq[Any](format.raw/*12.36*/("""
	
	"""),format.raw/*14.2*/("""<div id="mount"></div>
""")))}),format.raw/*15.2*/("""
	"""),format.raw/*50.3*/("""

"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


}

/*
 * This template takes a single argument, a String containing a
 * message to display.
 */
object index extends index_Scope0.index
              /*
                  -- GENERATED --
                  DATE: Mon Apr 04 01:52:40 EDT 2016
                  SOURCE: C:/Users/Asher/ticket-overlords/app/views/index.scala.html
                  HASH: 1fefdd010029fff6c9b41adc8db5a5fe2ccc25d8
                  MATRIX: 609->95|705->97|734->297|762->299|803->331|843->333|874->337|928->361|957->1489
                  LINES: 23->5|28->5|30->11|31->12|31->12|31->12|33->14|34->15|35->50
                  -- GENERATED --
              */
          