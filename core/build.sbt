name := "core"

scalaVersion := "3.3.1"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio-json" % "0.6.2",
  "org.scalatest" %% "scalatest" % "3.2.18" % Test
)
