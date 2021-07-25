package content

import zio.{ExitCode, URIO, ZIO}
import zio.system.System

object ProdutionGrade extends zio.App {

  import zio.config._
  import ConfigDescriptor._

  val systemEnvCfg = ConfigSource.fromSystemEnv // .provideLayer(System.live)

  val svc = "FUBAR"



  //
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
    ZIO.succeed(ExitCode.success)
  }

}


case class Postgres(
                     host: String,
                     port: Int,
                     db: String,
                     username: String,
                     password: String
                   )