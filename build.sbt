import Dependencies.Library._
import sbt.Keys._

name := """proxy-foscam"""
description := "Proxy for Foscam IP Camera"
organization := "joakim-ribier.fr"

version := "0.0.1"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .enablePlugins(BuildInfoPlugin)
  .settings(BuildInfo)
  .settings(libraryDependencies ++= Seq())

scalaVersion in ThisBuild := Dependencies.Versions.scalaVersion
resolvers in ThisBuild ++= Dependencies.resolvers

libraryDependencies in ThisBuild ++= Seq(
  ws,
  case_config,
  specs2 % Test
)

routesGenerator := InjectedRoutesGenerator

// Scala settings
scalacOptions in ThisBuild ++= Seq(
  "-deprecation",           // Warn when deprecated API are used
  "-feature",               // Warn for usages of features that should be importer explicitly
  "-unchecked",             // Warn when generated code depends on assumptions
  "-Ywarn-dead-code",       // Warn when dead code is identified
  "-Ywarn-numeric-widen"    // Warn when numeric are widened
)
scalacOptions in ThisBuild in (Compile, compile) ++= Seq( // Disable for tests
  "-Xlint",                 // Additional warnings (see scalac -Xlint:help)
  "-Ywarn-adapted-args"     // Warn if an argument list is modified to match the receive
)

lazy val BuildInfo = {
  Seq(
    buildInfoKeys := Seq[BuildInfoKey](name, version),
    buildInfoOptions += BuildInfoOption.BuildTime
  )
}
