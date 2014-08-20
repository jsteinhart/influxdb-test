scalaVersion := "2.11.2"

val influxdb =  RootProject(uri("https://github.com/lcycon/influxdb-scala.git"))

val root = project in file(".") dependsOn influxdb


libraryDependencies ++= Seq(
  "org.scalanlp" %% "breeze" % "0.10-SNAPSHOT",
  "org.scalanlp" %% "breeze-natives" % "0.10-SNAPSHOT",
  "com.github.nscala-time" %% "nscala-time" % "1.2.0",
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
  "ch.qos.logback" % "logback-classic" % "1.1.2"
)


resolvers ++= Seq(
            "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
            "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"
)
