ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.3.1"

// Déclaration des sous-projets
lazy val core = project.in(file("core"))
lazy val app = project.in(file("app")).dependsOn(core)

lazy val root = (project in file("."))
  .aggregate(core, app)
  .settings(
    name := "functional-graphs",
    publish / skip := true
  )
