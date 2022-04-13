import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.Filter
import io.restassured.filter.log.{RequestLoggingFilter, ResponseLoggingFilter}
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import utils.ConfVariables

import java.util
import scala.jdk.CollectionConverters.SeqHasAsJava


trait BaseConfiguration extends ConfVariables {

  def setupContext(path: String): Unit = {
    logger.info("Starting Configuration")
    RestAssured.requestSpecification = defaultRequestSpecification(path)
    logger.info("Finishing Configuration")
  }

  def defaultRequestSpecification(path: String): RequestSpecification = {
    val filters: util.List[Filter] = List(new RequestLoggingFilter(), new ResponseLoggingFilter()).asJava

    path match {
      case "auth" => new RequestSpecBuilder().setBaseUri(host).setBasePath(authPath).addFilters(filters).setContentType(ContentType.JSON).build()
      case "booking" => new RequestSpecBuilder().setBaseUri(host).setBasePath(bookingPath).addFilters(filters).setContentType(ContentType.JSON).build()
      case "ping" => new RequestSpecBuilder().setBaseUri(host).setBasePath(pingPath).addFilters(filters).setContentType(ContentType.JSON).build()
      case _ => new RequestSpecBuilder().setBaseUri(host).setBasePath("").addFilters(filters).setContentType(ContentType.JSON).build()
    }
  }

}
