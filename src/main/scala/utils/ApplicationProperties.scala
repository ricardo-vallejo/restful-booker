package utils

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.logging.log4j.scala.Logging

object ApplicationProperties extends Logging{

  logger.info("Reading property file")
  def config(fileName: String = "application.conf"): Config = {
    logger.info(s"Property file read ${fileName}")
    ConfigFactory.load(fileName).getConfig("endpoint")
  }
}
