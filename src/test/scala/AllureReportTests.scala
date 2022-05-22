import io.qameta.allure.scalatest.AllureScalatestContext
import io.restassured.RestAssured.`given`
import org.apache.http.HttpStatus
import org.hamcrest.Matchers.{everyItem, greaterThan, notNullValue}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec

class AllureReportTests extends AnyFlatSpec with BaseConfiguration with BeforeAndAfterAll {

  override protected def beforeAll(): Unit = setupContext("booking")

  "Get booking id" should "returns the ids" in new AllureScalatestContext {
    val response = `given`().when().get().`then`()

    response.statusCode(HttpStatus.SC_OK)
    response.body("bookingid", everyItem(notNullValue()))
    response.body("size()", greaterThan(Integer.valueOf(1)))
  }

}
