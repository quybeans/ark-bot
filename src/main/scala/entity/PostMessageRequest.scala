package entity

/**
  * Created by quybeans on 2/13/17.
  */
case class PostMessageRequest(
     token: String,
     channel: String,
     text: String,
     username: Option[String] = None,
     as_user: Boolean = true) { self =>
  def printMessage(username: String, text: String) = self.username + " : " + self.text

  def toMap(): Map[String,String] = {
    Map[String,String] (
      "token" -> self.token,
      "channel" -> self.channel,
      "text" -> self.text,
      //"username" -> self.get
      "as_user" -> self.as_user.toString
    )
  }
}
