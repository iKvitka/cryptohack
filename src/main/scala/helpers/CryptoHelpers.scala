package helpers

import java.nio.charset.StandardCharsets
import java.util.Base64
import scala.annotation.tailrec

object CryptoHelpers {
  implicit class AsciiHelper(array: IndexedSeq[Int]) {
    def toAsciiString: String = array.map(_.toChar).mkString
  }

  implicit class HexHelper(string: String) {
    def toByteArray: Array[Byte]    = string.map(_.toByte).toArray
    def hexToByteArray: Array[Byte] = BigInt(string, 16).toByteArray
    def hexToAscii: String          = string.hexToByteArray.toAsciiString
    def hexToBase64: String         = Base64.getEncoder.encodeToString(string.hexToByteArray)
    def toBase64: String         = Base64.getEncoder.encodeToString(string.toByteArray)
    def base64ToAsciiString: String = Base64.getDecoder.decode(string).toAsciiString
    def base64ToByteArray: Array[Byte] = Base64.getDecoder.decode(string)
    def rot13: String = string map {
      case c if 'a' <= c.toLower && c.toLower <= 'm' => (c + 13).toChar
      case c if 'n' <= c.toLower && c.toLower <= 'z' => (c - 13).toChar
      case c                                         => c
    }
  }

  implicit class ByteArrayHelper(byteArray: Array[Byte]) {
    def toUtf8String: String  = new String(byteArray, StandardCharsets.UTF_8)
    def toAsciiString: String = byteArray.map(_.toChar).mkString
    def toHexArrayString: String = byteArray.map("%02x".format(_)).mkString(" ")
  }

  implicit class BigIntHelper(bigInt: BigInt) {
    def extendedGcd(q: BigInt): (BigInt, BigInt, BigInt, BigInt, BigInt) = {
      val (old_r, r) = (bigInt, q)
      val (old_s, s) = (1, 0)
      val (old_t, t) = (0, 1)

      @tailrec
      def loop(
        rem: BigInt,
        oldRem: BigInt,
        u: BigInt,
        oldU: BigInt,
        v: BigInt,
        oldV: BigInt
      ): (BigInt, BigInt, BigInt) =
        if (rem == 0) (oldRem, oldU, oldV)
        else {
          val quotient = oldRem / rem
          loop(oldRem % rem, rem, oldU - quotient * u, u, oldV - quotient * v, v)
        }
      val res = loop(r, old_r, s, old_s, t, old_t)

      (bigInt, res._2, q, res._3, res._1)
    }
  }
}
