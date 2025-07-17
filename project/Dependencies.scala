import sbt._

// See: https://www.scala-sbt.org/1.x/docs/Organizing-Build.html
object Dependencies {

  // Versions
  lazy val scalaTestVersion = "3.2.19"
  lazy val catsCoreVersion = "2.12.0"
  lazy val catsEffectVersion = "3.5.6"
  lazy val declineVersion = "2.4.1"
  lazy val declineEffectVersion = "2.4.1"

  // Libraries
  val scalactic      = "org.scalactic"   %% "scalactic"      % scalaTestVersion
  val scalaTest      = "org.scalatest"   %% "scalatest"      % scalaTestVersion
  val catsCore       = "org.typelevel"   %% "cats-core"      % catsCoreVersion
  val catsEffect     = "org.typelevel"   %% "cats-effect"    % catsEffectVersion
  val decline        = "com.monovore"    %% "decline"        % declineVersion
  val declineEffect  = "com.monovore"    %% "decline-effect" % declineEffectVersion

  // Projects

  val writerdaemonDeps: Seq[ModuleID] =
      Seq(declineEffect, decline, catsEffect, catsCore, scalactic, scalaTest % Test)
}
