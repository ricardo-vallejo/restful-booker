package utils

import org.apache.logging.log4j.scala.Logging
import utils.ApplicationProperties.config

trait ConfVariables extends Logging {

  val host: String = config().getString("host")
  logger.info(s"Property load: ${host}")

  val authPath: String = config().getString("authPath")
  logger.info(s"Property load: ${authPath}")

  val bookingPath: String = config().getString("bookingPath")
  logger.info(s"Property load: ${bookingPath}")

  val pingPath: String = config().getString("pingPath")
  logger.info(s"Property load: ${pingPath}")

}
