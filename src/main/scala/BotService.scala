import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import _root_.entity.PostMessageRequest
import ulti.HttpBuilder

import scala.io.StdIn

object BotService extends App {


  implicit val botToken = "xoxb-139461255797-9qRvgw7urMq6FB5tauGELUXd"

  implicit val system = ActorSystem("ark-slack-bot")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

    val route = {
    pathEndOrSingleSlash{
      get{
        complete("Hi, this is Ark Maxim slack bot...")
      }
    }~
      path("login"){
        complete("Something nice")
    }~
      (path("slack-hook") & post) {

        entity(as[String]) {
          data => {
            val map =  data.split("&|\\=").toList.grouped(2).map { case List(k,v) => k -> v }.toMap
            println(map.get("user_name").get + " : " + ulti.Decode.utf8.decode(map.get("text").get))
          complete("Complete")
          }
        }
      }
    }

  new HttpBuilder(system, materializer)
    .sendSlackPostRequest(PostMessageRequest(botToken,"test-bot","Xin chào"))

  implicit val port = 8080
  val bindingFuture = Http().bindAndHandle(route,"localhost", port)
  println(s"\n Server started at port: $port \n Press Enter to stop ...")
  StdIn.readLine()

  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}

