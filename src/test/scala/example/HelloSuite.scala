package example

import java.nio.charset.Charset
import java.nio.file.{Path, Paths}

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

import scala.io.{BufferedSource, Codec, Source}

class HelloSuite extends AnyFunSuite with Matchers {
  test("say hello") {
    Hello.greeting mustBe "hello"
  }

  // Look up a work in the dictionary
  // Console , request input , give response
  val file: Path = Paths.get("/Users/dave/dev/dbuschman7/test19/src/test/resources/US.dic")
  println(file.toFile.exists())

  implicit val cp1252: Codec = Codec(Charset.forName("Cp1252"))
  val src: BufferedSource = Source.fromFile(file.toFile)
  try {
    val raw: Seq[String] = src.getLines().toSeq
    println(raw.size)

    val input = "whelp"

    println(" enter a word to search >")
    val inputRaw: String = scala.io.StdIn.readLine()

    Option(inputRaw)
      .map(_.trim)
      .map { word =>
        val result = raw.contains(word)
        println(s"The dictionary does ${if (result) "" else "NOT "}contain the word '${word}'")
      }

  } finally {
    src.close()
  }


  //  println(pwd.getAbsolutePath
  //  val src = Source.fromFile(file)


}
