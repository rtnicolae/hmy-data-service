import sbt._

object Dependencies {
  object v {
    val ZIO = "1.0.12"
    val ZIOJson = "latest.release"
    val PureConfig = "0.16.0"
    val Logback = "1.2.3"
    val Logging = "3.9.4"
    val Requests = "0.7.0"
    val BouncyCastle = "1.70"
    val ScalaTest = "3.2.11"
  }

  object lib {
    //ZIO
    object zio {
      val Core = "dev.zio" %% "zio" % v.ZIO
      val Streams = "dev.zio" %% "zio-streams" % v.ZIO
      val Json = "dev.zio" %% "zio-json" % v.ZIOJson
    }

    object logging {
      val ScalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % v.Logging
      // Logback - a logger implementation for the slf4j logging adapter
      val Logback = "ch.qos.logback" % "logback-classic" % v.Logback
    }

    val Requests = "com.lihaoyi" %% "requests" % v.Requests
    val PureConfig = "com.github.pureconfig" %% "pureconfig" % v.PureConfig
    object bouncyCastle {
      val Kix = "org.bouncycastle" % "bcpkix-jdk15on" % v.BouncyCastle
      val Rov = "org.bouncycastle" % "bcprov-jdk15on" % v.BouncyCastle
    }

    val ScalaTest = "org.scalatest" %% "scalatest" % v.ScalaTest % "test"
  }

  val main = Seq(
    lib.zio.Core,
    lib.zio.Streams,
    lib.zio.Json,
    lib.PureConfig,
    lib.Requests,
    lib.ScalaTest,
    lib.logging.Logback,
    lib.logging.ScalaLogging,
    lib.bouncyCastle.Kix,
    lib.bouncyCastle.Rov,
  )
}
