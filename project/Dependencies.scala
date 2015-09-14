import sbt._

object Dependencies {

  object Versions {
    val scalaVersion = "2.11.7"
  }

  val resolvers = Seq(
    "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/", // Play things (outside Play project)
    "Pellucid Bintray" at "http://dl.bintray.com/pellucid/maven", // caseConfig
    "Scalaz bintray" at "http://dl.bintray.com/scalaz/releases",
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  )

  object Library {

    object Play {
      val version = play.core.PlayVersion.current
    }

    val case_config = "com.pellucid" %% "case-config" % "0.1.2"
  }
}
