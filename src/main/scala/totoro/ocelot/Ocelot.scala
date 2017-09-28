package totoro.ocelot

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

/**
  * Entry point of application
  */

object Ocelot {
  def main(args: Array[String]) {
    implicit val system: ActorSystem = ActorSystem("ocelot-system")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val route = get {
      (pathEndOrSingleSlash & redirectToTrailingSlashIfMissing(StatusCodes.TemporaryRedirect)) {
        getFromResource("index.html")
      } ~ {
        getFromResourceDirectory("")
      }
    }

    val bindingFuture = Http().bindAndHandle(route, Config.Host, Config.Port)

    println(s"[ ocelot.online v${BuildInfo.version} ]")
    println(s"Server online at http://${Config.Host}:${Config.Port}/\nPress RETURN to stop...")

    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
