package content

import zio.duration._
import zio.{Exit, ExitCode, UIO, URIO, ZIO}

object ZioFibers extends zio.App {


  // IO monad
  // ZIO[ R, E, A]

  val zmol: UIO[Int] = ZIO.succeed(42)

  // concurrency
  val showerTime = ZIO.succeed("taking a shower")
  val boilingWater = ZIO.succeed("boiling water ")
  val preparingCoffee = ZIO.succeed("Prepare some coffee")

  def printThread = s"[${Thread.currentThread().getName}]"


  def syncRoutine() = for {
    _ <- showerTime.debug(printThread)
    _ <- boilingWater.debug(printThread)
    _ <- preparingCoffee.debug(printThread)
  } yield ()


  // fiber - schedulable computation
  def cncurrentBathBoilWater() = for {
    _ <- showerTime.debug(printThread).fork
    _ <- boilingWater.debug(printThread)
    _ <- preparingCoffee.debug(printThread)
  } yield ()


  def conncurrentRoutine() = for {
    showerFiber <- showerTime.debug(printThread).fork
    boilingWaterFiber <- boilingWater.debug(printThread).fork
    zippedFiber = showerFiber zip boilingWaterFiber
    result <- zippedFiber.join.debug(printThread)
    _ <- ZIO.succeed(s"$result done").debug(printThread) *> preparingCoffee.debug(printThread)
  } yield ()

  val callFromALice = ZIO.succeed("Call from Alice")
  val boilingWaterWIthTime = boilingWater.debug(printThread) *> ZIO.sleep(5.seconds) *> ZIO.succeed("Boiled water ready")


  def conncurrentWithAlice = for {
    _ <- showerTime.debug(printThread)
    boilingFiber <- boilingWaterWIthTime.fork
    _ <- callFromALice.debug(printThread).fork *> ZIO.sleep(2.seconds) *> boilingFiber.interrupt.debug(printThread)
    _ <- ZIO.succeed("Screw my coffee, going with Alice").debug(printThread)
  } yield ()

  val prepareCoffreWithTime = preparingCoffee.debug(printThread) *> ZIO.sleep(5.seconds) *> ZIO.succeed("Coffee Ready")

  def concurrentRoutineWithCoffeeAtHome() = for {
    _ <- showerTime.debug(printThread)
    _ <- boilingWater.debug(printThread)
    coffeeFiber <- prepareCoffreWithTime.debug(printThread).fork.uninterruptible
    result <- callFromALice.debug(printThread).fork *> coffeeFiber.interrupt.debug(printThread)
    _ <- result match {
      case Exit.Success(_) => ZIO.succeed("Sorry Alice, making breakfast at home ").debug(printThread)
      case _ => ZIO.succeed("Going to a cafe with Alice")
    }} yield ()


  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
//    syncRoutine().exitCode
//    cncurrentBathBoilWater().exitCode
//    conncurrentRoutine.exitCode
//    conncurrentWithAlice.exitCode
    concurrentRoutineWithCoffeeAtHome.exitCode
  }
}
