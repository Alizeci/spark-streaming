version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.6"

val sparkVersion = "3.5.0"

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion
libraryDependencies += "org.apache.spark" % "spark-streaming_2.13" % sparkVersion % "provided"
libraryDependencies += "org.apache.spark" % "spark-streaming-kafka-0-10_2.13" % sparkVersion
libraryDependencies += "org.apache.spark" % "spark-sql-kafka-0-10_2.13" % sparkVersion

lazy val root = (project in file("."))
  .settings(
    name := "pfsd-spark-streaming"
  )
