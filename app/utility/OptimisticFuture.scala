package utility

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._

// Used to convert a Seq[Future[T]] to Future[Seq[T]] while gracefully recovering from failures
object OptimisticFuture {
  def sequence[A](source: Seq[Future[A]]): Future[Seq[A]] = {
    val optioned = source.map { fut =>
      fut.map(Option.apply).recover {
        case _ => None: Option[A]
      }
    }
    
    Future.sequence(optioned).map(_.flatten)
  }
}