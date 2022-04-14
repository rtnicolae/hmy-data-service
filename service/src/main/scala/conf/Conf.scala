package conf

import com.typesafe.scalalogging.LazyLogging
import conf.Conf.HmyRpcConfig
import pureconfig._
import pureconfig.generic.auto._

case class Conf(rpc: HmyRpcConfig)

object Conf extends LazyLogging {
  case class HmyRpcConfig(endpoint: String)

  def apply(resource: String = "application.conf"): Conf =
    ConfigSource.resources(resource).load[Conf] match {
      case Right(config) => config
      case Left(failures) =>
        val msg = s"Unable to load service configuration"
        logger.error(s"$msg:\n${failures.toList.map(_.description).mkString("* ", "\n* ", "")}")
        throw new IllegalStateException(msg)
    }
}
