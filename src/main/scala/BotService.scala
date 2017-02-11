import akka.actor.ActorSystem
import akka.http.javadsl.unmarshalling.Unmarshaller
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import akka.http.scaladsl.common.StrictForm
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.unmarshalling.Unmarshal
import _root_.entity.HookResponse

import scala.concurrent.Future
import scala.io.StdIn

object BotService extends App {

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
//        parameter('token.as[String],
//        'team_id.as[String],
//        'team_domain.as[String],
//        'service_id.as[String],
//        'channel_id.as[String],
//        'channel_name.as[String],
//        'timestamp.as[String],
//        'user_id.as[String],
//        'user_name.as[String],
//        'text.as[String]).as(HookResponse) { response =>
//        println(response.text)
//        complete("Complete")
//        }

        entity(as[String]) { data=> {
            println(data)
          complete("Complete")
          }
        }
      }
    }

  implicit val port = 8080
  val bindingFuture = Http().bindAndHandle(route,"localhost", port)
  println(s"\n Server started at port: $port \n Press Enter to stop ...")
  StdIn.readLine()

  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}

