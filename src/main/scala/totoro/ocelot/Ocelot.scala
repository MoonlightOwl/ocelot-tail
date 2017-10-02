package totoro.ocelot

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import totoro.ocelot.http.HttpServer

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

/**
  * Entry point of application
  */

object Ocelot {
  def main(args: Array[String]) {
    implicit val system: ActorSystem = ActorSystem("ocelot-system")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    println(s"[ ocelot.online v${BuildInfo.version} ]")

    // start web server
    val httpServer = new HttpServer()

    // let it run until user presses return
    StdIn.readLine()

    // stop everything
    httpServer.stop().onComplete(_ => system.terminate())
  }
}
