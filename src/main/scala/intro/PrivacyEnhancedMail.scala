package intro

import helpers.CryptoHelpers.{ByteArrayHelper, HexHelper}

import java.nio.file.Files
import java.nio.file.Paths

object PEM {
  def decode(pemData:String): Unit = {
    val start = "\\s*-----BEGIN (.*)-----\\n".r
    if (start.findFirstIn(pemData).isEmpty) throw new Exception("Not a valid PEM pre boundary")

    val end = "-----END (.*)-----\\s*$".r
    if (end.findFirstIn(pemData).isEmpty) throw new Exception("Not a valid PEM post boundary")

    val clean = pemData.replaceAll(" ","").split("\\s").drop(1).dropRight(1).mkString.base64ToByteArray
    println(clean.mkString("Array(", ", ", ")"))
    println(clean.toHexArrayString)
  }
}

object PrivacyEnhancedMail extends App {

  val keyBytes: Array[Byte] = Files.readAllBytes(Paths.get(getClass.getResource("/privacy_enhanced_mail.pem").getPath))
  println(PEM.decode(keyBytes.toAsciiString))

}
