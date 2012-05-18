import sbt._
import sbt.Keys._

object RingBenchmarkInAkkaBuild extends Build {

  lazy val ringBenchmarkInAkka = Project(
    id = "ring-benchmark-in-akka",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "Ring Benchmark in Akka",
      organization := "org.everpeace.ringbench",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.9.2",
      resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases",
      libraryDependencies += "com.typesafe.akka" % "akka-actor" % "2.0.1"
    )
  )
}
