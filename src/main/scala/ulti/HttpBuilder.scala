package ulti

import akka.actor.{ActorSystem}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.Uri.Query
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, Uri}
import akka.stream.ActorMaterializer
import entity.PostMessageRequest

import scala.concurrent.Future


class HttpBuilder(
  actorSystem: ActorSystem,
  materializer: ActorMaterializer
) { self =>
  implicit val postMessageHttp = "https://slack.com/api/chat.postMessage"

  def makeHttpRequest(url: String): Future[HttpResponse] ={
    Http(system = self.actorSystem).singleRequest(HttpRequest(uri = url))(fm = self.materializer)
  }

  def sendSlackPostRequest(request: PostMessageRequest) = {
    val url = Uri(postMessageHttp).withQuery(Query(request.toMap()))
    makeHttpRequest(url.toString())
  }


}
