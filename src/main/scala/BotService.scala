import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
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

        entity(as[String]) { data=> {
            //println(data)
          data.split("&")
            .foreach(item => item.split("=")
              .foreach(head => {
                if (head.equals("text"))
                  println(item.split("=")(1))
              })
            )
          
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

