package intro

import helpers.CryptoHelpers.AsciiHelper

object XorStarter extends App{
  val message = "label"

  println(message.map(_ ^ 13).toAsciiString)
}
