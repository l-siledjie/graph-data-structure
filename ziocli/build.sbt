//ThisBuild / version := "0.1.0-SNAPSHOT"
//
//ThisBuild / scalaVersion := "3.3.1"

name := "ziocli"

scalaVersion := "3.3.1"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % "2.1.19",
  "dev.zio" %% "zio-streams" % "2.1.19", // if you do streaming I/O
  "dev.zio" %% "zio-json" % "0.7.3",
  "com.monovore" %% "decline" % "2.4.1" // optional: CLI parsing
)
