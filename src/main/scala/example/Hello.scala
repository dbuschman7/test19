package example

object Hello extends Greeting with App {
  println(greeting)

  val stream = 1 #:: 2 #:: Stream.empty
  val stream2 = (1 to 10).toStream

  val foo6 = stream2.collect{ case in if in % 2  == 0 =>  in}
  foo6.take(5).toList.map(println)

}

trait Greeting {
  lazy val greeting: String = "hello"
}
