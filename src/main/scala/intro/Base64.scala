package intro

import helpers.CryptoHelpers.HexHelper

object Base64 extends App {
  val hex = "72bca9b68fc16ac7beeb8f849dca1d8a783e8acf9679bf9269f7bf"

  println(hex.hexToBase64)
}
