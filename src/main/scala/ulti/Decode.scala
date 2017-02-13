package ulti

import scala.util.Try

/**
  * Created by quybeans on 2/12/17.
  */
object Decode {
  import java.net.URLDecoder
  import java.nio.charset.Charset

  trait Extract {
    def charset: Charset
    def unapply(raw: String) =
      Try(URLDecoder.decode(raw, charset.name())).toOption.get
  }

  object utf8 extends Extract {
    val charset = Charset.forName("utf8")
  }
}
