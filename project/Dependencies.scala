import sbt._

object Dependencies {
  val zioVersion = "1.0.9"


  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.9"

  lazy val zio = "dev.zio" %% "zio" % zioVersion
  lazy val zioStreams = "dev.zio" %% "zio-streams" % zioVersion
  lazy val zioConfig = "dev.zio" %% "zio-config" % "1.0.6"
  lazy val zioLogging = "dev.zio" %% "zio-logging" % "0.5.11"
  lazy val zioMetrics= "dev.zio" %% "zio-metrics" % "1.0.12"
//  lazy val zioZmx = "dev.zio" %% "zio-zmx" % zioVersion
  lazy val rezilience = "nl.vroste" %% "rezilience" % "0.6.2"
}
