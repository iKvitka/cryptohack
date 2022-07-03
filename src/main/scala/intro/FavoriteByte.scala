package intro

import helpers.CryptoHelpers.{AsciiHelper, HexHelper}

object FavoriteByte extends App {
  val hex = "73626960647f6b206821204f21254f7d694f7624662065622127234f726927756d"

  val message = hex.hexToAscii
  println(message)

  (0 to 255).foreach { b =>
    println(message.map(_ ^ b).toAsciiString)
  }

  val key = message(0) ^ 'c'
  println(s"key: $key")

  println(message.map(_ ^ key).mkString(","))
}
