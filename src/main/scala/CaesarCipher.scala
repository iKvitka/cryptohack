import scala.io.StdIn.readLine

object CaesarCipher extends App {
  val in = readLine

  def decode(message: String, shift: Int = 3) = {
    def transform(letter: Int) = if (letter < 0) letter + 26 else letter
    message.map { l =>
      if (l.isUpper) (transform(l - 'A' - (shift % 26)) + 'A').toChar
      else if (l.isLower) (transform(l - 'a' - (shift % 26)) + 'a').toChar
      else l
    }
  }

  (0 to 27).foreach(x=>println(decode(in, x)))
}
