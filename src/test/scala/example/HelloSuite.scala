package example

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class HelloSuite extends AnyFunSuite with Matchers {
  test("say hello") {
    Hello.greeting mustBe "hello"
  }
}
