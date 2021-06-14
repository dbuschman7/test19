package example

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class HelloSpec extends AnyFunSuite with Matchers {
  test("say hello") {
    Hello.greeting mustBe "hello"
  }
}
