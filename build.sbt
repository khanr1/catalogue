//name := """Catalogue"""
//organization := "com.catalogue"
ThisBuild / scalaVersion :="2.13.6"
ThisBuild / organization :="com.catalogue"
ThisBuild / version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala).settings(
  name:="Catalogue",
  libraryDependencies ++= Seq(guice,
    "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
    "com.typesafe.play" %% "play-slick" % "5.0.0",
    "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
    "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
    "com.h2database" % "h2" % "1.4.199",
    "org.typelevel" %% "cats-core" % "2.3.0",
    "org.webjars" % "jquery" % "3.6.0",
    "org.webjars" % "bootstrap" % "5.1.1"
  ),
)



// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.catalogue.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.catalogue.binders._"
