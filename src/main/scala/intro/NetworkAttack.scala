package intro

import helpers.CryptoHelpers.ByteArrayHelper

import java.net.Socket

object NetworkAttack extends App {
  val host   = "socket.cryptohack.org"
  val port   = 11112
  val socket = new Socket(host, port)

  val message: Array[Byte] = """{"buy": "flag"}""".map(_.toByte).toArray
  socket.getOutputStream.write(message)
  lazy val response: Array[Byte] = socket.getInputStream.readAllBytes()

  println(response.toAsciiString)
}
