package content

import zio.config._
import zio.console._
import zio.{App, ExitCode, Has, URIO, ZEnv, ZIO}

import ConfigDescriptor._

final case class Prod(ldap: String, port: Int, dburl: Option[String])


object Prod {
  val prodConfig: ConfigDescriptor[Prod] =
    (string("LDAP") |@| int("PORT") |@|
      string("DB_URL").optional)(Prod.apply, Prod.unapply)

  val myAppLogic: ZIO[Has[Prod], Throwable, Prod] =
    for {
      prod <- getConfig[Prod]
    } yield prod
}


object ReadConfig extends App {

  override def run(args: List[String]): URIO[ZEnv, ExitCode] =
    for {
      console    <- ZIO.environment[Console].map(_.get)
      configLayer = ZConfig.fromMap(
        Map("LDAP" -> "ldap", "PORT" -> "1999", "DB_URL" -> "ddd"),
        Prod.prodConfig,
        "constant"
      )
      out        <- Prod.myAppLogic
        .provideLayer(configLayer)
        .foldM(
          failure => console.putStrLn(failure.toString),
          cfg => {
            console.putStrLn("Success - '" + cfg + "'")
          }
        )
        .exitCode
    } yield out
}