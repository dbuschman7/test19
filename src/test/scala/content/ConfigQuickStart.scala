package content

import zio.config._
import ConfigDescriptor._
import zio.{App, ExitCode, Has, Layer, URIO}
import zio.system.System

case class MyConfig(ldap: String, port: Int, dburl: String)


object ConfigQuickStart extends App {

  val myConfig: ConfigDescriptor[MyConfig] =
    (string("LDAP") |@| int("PORT") |@| string("DB_URL")) (MyConfig.apply, MyConfig.unapply)

  val myConfigTupled: ConfigDescriptor[(String, Int, String)] =
    (string("LDAP") |@| int("PORT") |@| string("DB_URL")).tupled

  val map =
    Map(
      "LDAP" -> "xyz",
      "PORT" -> "8888",
      "DB_URL" -> "postgres"
    )

  val source: _root_.zio.config.ConfigSource = ConfigSource.fromMap(map)

  read(myConfig from source) // Either[ReadError[String], MyConfig]

  val result: Layer[ReadError[String], Has[MyConfig]] =
    ZConfig.fromMap(map, myConfig)

  val betterConfig: _root_.zio.config.ConfigDescriptor[MyConfig] =
    (string("LDAP") ?? "Related to auth" |@|  int("PORT") ?? "Database port" |@|
      string("DB_URL") ?? "url of database"
      )(MyConfig.apply, MyConfig.unapply)

  val foo = generateDocs(betterConfig).toTable.toGithubFlavouredMarkdown
  println(foo)

  val result2: Layer[ReadError[String], Has[MyConfig]] = System.live >>> ZConfig.fromSystemEnv(myConfig)

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = ???


}


