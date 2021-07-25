import Dependencies._

lazy val scala211 = "2.11.12"
lazy val scala212 = "2.12.14"
lazy val scala213 = "2.13.5"
lazy val scala3  = "3.0.0"
lazy val supportedScalaVersions = List(scala213, scala212)


ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

ThisBuild / scalaVersion := scala213
ThisBuild / crossScalaVersions := supportedScalaVersions

lazy val root = (project in file("."))
  .settings(
    name := "test19",
    libraryDependencies ++= Seq(zio, zioStreams, zioConfig, zioLogging, zioMetrics, rezilience),
    libraryDependencies += scalaTest % Test,
    connectInput in run := true
  )
