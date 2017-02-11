package entity

import akka.http.scaladsl.unmarshalling.{FromRequestUnmarshaller, Unmarshaller}

/**
  * Created by quybeans on 2/10/17.
  */
case class HookResponse(
     token: String,
     team_id: String,
     team_domain: String,
     service_id:String,
     channel_id: String,
     channel_name: String,
     timestamp: String,
     user_id: String,
     user_name: String,
     text: String)
