package intro

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.PngWriter
import com.sksamuel.scrimage.pixels.Pixel
import com.sksamuel.scrimage.transform.Transform

import java.io.File

class XorImage(immutableImage: ImmutableImage) extends Transform {

  override def apply(input: ImmutableImage): ImmutableImage = {
    val result = input.copy()
    val pixels = immutableImage.pixels()

    input
      .pixels()
      .zipWithIndex
      .map(p => new Pixel(p._1.x, p._1.y, p._1.toARGBInt ^ pixels(p._2).toARGBInt))
      .foreach(result.setPixel)

    result
  }

}

object Lemur extends App {
  val flag  = ImmutableImage.loader().fromResource("/flag.png")
  val lemur = ImmutableImage.loader().fromResource("/lemur.png")

  val res = flag.transform(new XorImage(lemur))

  res.output(PngWriter.NoCompression, new File("spaghetti.png"))
}
