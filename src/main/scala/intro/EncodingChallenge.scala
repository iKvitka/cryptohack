package intro

import helpers.CryptoHelpers.{ByteArrayHelper, HexHelper}
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json._

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket
import scala.language.postfixOps

case class Question(`type`: String, encoded: String)
case class Answer(decoded: String)

object Question {
  val encodedReader: Reads[String] = {
    case JsString(value) => JsSuccess(value)
    case JsArray(value)  => JsSuccess(value.map(_.as[Byte]).toArray.toUtf8String)
    case _               => JsError("lol what are you doing?")
  }
  implicit val reads: Reads[Question] = (
    (__ \ "type").read[String] and
      (__ \ "encoded").read[String](encodedReader)
  )(Question.apply _)
}
object Answer {
  implicit val format: Format[Answer] = Json.format[Answer]
}

object EncodingChallenge extends App {
  val host   = "socket.cryptohack.org"
  val port   = 13377
  val socket = new Socket(host, port)

  val stream = new BufferedReader(new InputStreamReader(socket.getInputStream))

  (1 to 100).foreach { i =>
    println(i)
    lazy val response = stream.readLine()

    val question = Json.parse(response).as[Question]

    val answer = Answer(question.`type` match {
      case "base64" => question.encoded.base64ToAsciiString
      case "hex"    => question.encoded.hexToAscii
      case "rot13"  => question.encoded.rot13
      case "bigint" => question.encoded.replace("0x", "").hexToAscii
      case "utf-8"  => question.encoded
    })

    println(response)
    println(question)
    println(answer)

    socket.getOutputStream.write(Json.stringify(Json.toJson(answer)).toByteArray)
  }
  println(stream.readLine())
}
