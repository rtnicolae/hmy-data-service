ThisBuild / organization := "net.ranicolae"
ThisBuild / scalaVersion := "2.13.8"

ThisBuild / publish / skip := true
ThisBuild / publishMavenStyle := false

Global / onChangedBuildSource := ReloadOnSourceChanges
scalafmtOnCompile := true

lazy val `service` = project
  .settings(mainSettings)
  .settings(libraryDependencies ++= Dependencies.main)
val mainSettings = Seq(
  scalacOptions := Seq(
    "-deprecation",
    "-encoding",
    "utf8",
    "-feature",
    "-language:implicitConversions",
    "-unchecked",
    "-Xfatal-warnings",
  )
)
