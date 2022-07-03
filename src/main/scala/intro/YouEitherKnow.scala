package intro

import helpers.CryptoHelpers.{AsciiHelper, ByteArrayHelper, HexHelper}

object YouEitherKnow extends App {
  // Bullshit

  val hex = "0e0b213f26041e480b26217f27342e175d0e070a3c5b103e2526217f27342e175d0e077e263451150104"
  val key = s"crypto{${0.toChar.toString * 34}}".map(_.toByte)
//  val key = BigInt("crypto{FLAG}".toByteArray.mkString)
  val number = BigInt(hex, 16)

  val message = hex.hexToByteArray
  println(message.length)
  println(key.size)
  println(message.mkString("Array(", ", ", ")"))
  println(key)
//  println(message^key)

  val partialKey = message.indices.map(i => message(i) ^ key(i))

  println(partialKey.zipWithIndex)
  val keyString = partialKey.take(7).toAsciiString + partialKey.takeRight(1).toAsciiString
  println(partialKey.take(7).toAsciiString + "..." + partialKey.takeRight(1).toAsciiString)

  val flag = message.drop(7).dropRight(1)

  println(flag.mkString("Array(", ", ", ")"))

  val finalKey = keyString * 5 + "my"

  val res = message.indices.map(i => message(i) ^ finalKey(i))
  println(res.toAsciiString)
//  (0 to 255).map{ b =>
//    (number^b).toByteArray.toAsciiString
//  }.foreach(println)

//  println((number ^ key).toByteArray.toAsciiString)
}
