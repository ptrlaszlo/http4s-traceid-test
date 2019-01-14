import com.typesafe.sbt.GitVersioning
import sbt._
import sbt.Keys._
import sbtrelease.ReleaseStateTransformations._
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import com.typesafe.sbt.packager.universal.UniversalPlugin
import com.typesafe.sbt.packager.universal.UniversalPlugin.autoImport._

lazy val `tracing-test` = project.in(file("."))
  .settings(libraryDependencies ++= Dependencies.all)
  .settings(compilerSettings)
  .settings(
    mainClass in (Compile,run) := Some("test.Main"),
    topLevelDirectory := None,
    name in Universal := name.value
  )

lazy val compilerSettings = Seq(
  scalaVersion := "2.12.4",
  scalacOptions ++= Seq(
    "-unchecked",
    "-deprecation",
    "-encoding", "utf8",
    "-feature",
    "-explaintypes",
    "-target:jvm-1.8",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-Ydelambdafy:method",
    "-Xcheckinit",
    "-Xfuture",
    "-Xlint",
    "-Xlint:-nullary-unit",
    "-Ywarn-unused",
    "-Ywarn-dead-code",
    "-Ywarn-value-discard",
    "-Ypartial-unification"
  ),
  addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4"),
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full),
)
