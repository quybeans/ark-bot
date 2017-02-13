import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import scala.io.StdIn

object BotService extends App {

  implicit val system = ActorSystem("ark-slack-bot")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher


    def printMessage(name: String, text: String) = println(name + " : " + text)

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
          def getText(x:Array[String]) =
            if (x(0).equals("text")) {
              print(" : " + ulti.Decode.utf8.unapply(x(1)) + "\n")
            } else if (x(0).equals("user_name")) {
              print(ulti.Decode.utf8.unapply(x(1)))
            } else None

          val rs = data.split("&").foreach(rs => getText(rs.split("=")))

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

