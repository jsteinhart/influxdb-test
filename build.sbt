scalaVersion := "2.10.4"

val influxdb =  RootProject(uri("https://github.com/influxdb/influxdb-scala.git"))

val root = project in file(".") dependsOn influxdb


libraryDependencies ++= Seq(
  "org.scalanlp" %% "breeze" % "0.10-SNAPSHOT",
  "org.scalanlp" %% "breeze-natives" % "0.10-SNAPSHOT",
  "org.json4s" %% "json4s-native" % "3.2.10",
  "com.github.nscala-time" %% "nscala-time" % "1.2.0"
)


resolvers ++= Seq(
            "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
            "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"
)
