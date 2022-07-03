package intro

import helpers.CryptoHelpers.ByteArrayHelper

object XorProperties extends App{
  val KEY1 = BigInt("a6c8b6733c9b22de7bc0253266a3867df55acde8635e19c73313",16)
  val KEY23 = BigInt("c1545756687e7573db23aa1c3452a098b71a7fbf0fddddde5fc1",16)
  val flag123 = BigInt("04ee9855208a2cd59091d04767ae47963170d1660df7f56f5faf",16)

  println((flag123 ^ KEY23 ^ KEY1).toByteArray.toAsciiString)
}
