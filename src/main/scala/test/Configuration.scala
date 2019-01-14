package test

import test.Configuration._
import com.typesafe.config.{Config, ConfigFactory}
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ArbitraryTypeReader._

import scala.concurrent.duration.FiniteDuration

case class Configuration(config: Config = ConfigFactory.load().getConfig("app")) {

  val httpConfig: HttpTimeoutConfig = config.as[HttpTimeoutConfig]("http")
}

object Configuration {
  case class HttpTimeoutConfig(requestTimeout: FiniteDuration, idleTimeout: FiniteDuration)
}
