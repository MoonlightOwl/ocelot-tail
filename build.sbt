/* project signature */
name := "ocelot-tail"
version := "0.1.1"

/* language and dependencies */
scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.0.10",
  "com.typesafe" % "config" % "1.3.1"
)

/* make project name and version accessible from source code */
lazy val ocelot = (project in file(".")).
  enablePlugins(BuildInfoPlugin).
  settings(
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "totoro.ocelot"
  )

/* configure fat jar */
mainClass in assembly := Some("totoro.ocelot.Ocelot")
test in assembly := {}
assemblyOutputPath in assembly := file(s"target/ocelot-${version.value}.jar")
