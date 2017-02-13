name := "ark-facts-slackbot"

version := "1.0"

scalaVersion := "2.12.1"

val akkaHttpVersion = "10.0.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-core",
  "com.typesafe.akka" %% "akka-http"
).map(_ % akkaHttpVersion)

libraryDependencies +=
  "com.typesafe.akka" %% "akka-actor" % "2.4.16"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"