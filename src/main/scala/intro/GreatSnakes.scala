package intro

object GreatSnakes extends App {
  val ords = List(81, 64, 75, 66, 70, 93, 73, 72, 1, 92, 109, 2, 84, 109, 66, 75, 70, 90, 2, 92, 79)

  println("Here is your flag:")
  println(ords.map(o => (o ^ 0x32).toChar).mkString)
}
