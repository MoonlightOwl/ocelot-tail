package totoro.ocelot.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import totoro.ocelot.Config

import scala.concurrent.{ExecutionContextExecutor, Future}

/**
  * Share SPA web page with OpenComputers terminal window
  */

class HttpServer(implicit val system: ActorSystem, implicit val materializer: ActorMaterializer) {
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val route: Route = get {
    (pathEndOrSingleSlash & redirectToTrailingSlashIfMissing(StatusCodes.TemporaryRedirect)) {
      getFromResource("index.html")
    } ~ {
      getFromResourceDirectory("")
    }
  }
  val bindingFuture: Future[Http.ServerBinding] = Http().bindAndHandle(route, Config.Host, Config.Port)

  println(s"Server online at http://${Config.Host}:${Config.Port}/\nPress RETURN to stop...")

  def stop(): Future[Unit] = {
    // trigger unbinding from the port
    bindingFuture.flatMap(_.unbind())
  }
}
