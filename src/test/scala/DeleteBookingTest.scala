import io.restassured.RestAssured.`given`
import io.restassured.http.ContentType
import org.apache.http.HttpStatus
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite

class DeleteBookingTest extends AnyFunSuite with BaseConfiguration with BeforeAndAfterAll {

  override protected def beforeAll(): Unit = setupContext("booking")

  test("Delete a pet user") {
    val url = "https://petstore.swagger.io/v2/user/avallejo"

    `given`()
      .contentType(ContentType.JSON)
      .when()
      .delete(url)
      .`then`()
      .statusCode(HttpStatus.SC_OK)
  }
}
