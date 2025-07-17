import Dependencies._

ThisBuild / organization  := "org.kelseymountain"
ThisBuild / version       := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion  := "3.3.4"  // Intended to be the latest LTS version.
ThisBuild / scalacOptions :=
  Seq("-encoding", "UTF-8",   // Encoding of the source files.
      "-feature",
      "-deprecation",         // Tell us about deprecated things.
      "-unchecked")

Compile / run / fork := true  // Run in a forked JVM for Cats Effect compatibility.
Test / logBuffered := false   // Don't buffer test output so test messages make sense.

lazy val agc = (project in file("."))
  .settings(
    name := "WriterDaemon",
    libraryDependencies ++= writerdaemonDeps,

    // This is how to included unmanaged JAR files (not managed by SBT) from the lib folder.
    // Test / unmanagedJars += file("lib/something.something.something.jar"),
  )
