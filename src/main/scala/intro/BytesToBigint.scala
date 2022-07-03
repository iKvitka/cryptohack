package intro

import helpers.CryptoHelpers.ByteArrayHelper

object BytesToBigint extends App{
  val message: BigInt = BigInt("11515195063862318899931685488813747395775516287289682636499965282714637259206269")
  println(message.toByteArray.toAsciiString)
}
