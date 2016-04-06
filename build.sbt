name := """ticket-overlords"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test,
  "org.webjars" % "jquery" % "2.1.4",
  "com.typesafe.play" %% "play-slick-evolutions" % "1.0.0",
  "com.h2database" % "h2" % "1.4.187",
  "com.typesafe.slick" %% "slick" % "3.0.0",
  //"org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.webjars" % "requirejs" % "2.1.18",
  "org.webjars" % "react" % "0.13.3"
)
//pipelineStages := Seq(rjs)
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
routesGenerator := StaticRoutesGenerator

