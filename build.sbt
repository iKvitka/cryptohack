ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name                                           := "cryptohack",
    libraryDependencies += "com.typesafe.play"     %% "play-json"      % "2.9.2",
    libraryDependencies += "com.sksamuel.scrimage"  % "scrimage-core"  % "4.0.30",
    libraryDependencies += "com.sksamuel.scrimage" %% "scrimage-scala" % "4.0.30"
  )
