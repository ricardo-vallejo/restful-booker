import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import faker.Faker
import io.restassured.RestAssured.`given`
import io.restassured.http.ContentType
import model.{BookingDates, BookingRequest}
import org.apache.http.HttpStatus
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite

import java.time.format.DateTimeFormatter

class UpdateBookingTest extends AnyFunSuite with BaseConfiguration with BeforeAndAfterAll {

  override protected def beforeAll(): Unit = setupContext("booking")

  test("Update a booking") {
    val mapper = JsonMapper.builder().addModule(DefaultScalaModule).build()

    val newBooking = BookingRequest(
      Faker.default.firstName(),
      Faker.default.lastName(),
      100,
      true,
      BookingDates(
        Faker.default.nowLocalDateTime().format(DateTimeFormatter.ISO_DATE),
        Faker.default.nowLocalDateTime().plusDays(5L).format(DateTimeFormatter.ISO_DATE)),
      Faker.default.buzzWord()
    )

    mapper.writeValueAsString(newBooking)

    given
      .body(newBooking)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
      .header("Authorisation", "Basic YWRtaW46cGFzc3dvcmQxMjM=]")
      .cookie("token", "7440fcd6aa21a1c")
      .when()
      .put("/6640")
      .`then`()
      .statusCode(HttpStatus.SC_OK)
  }

  test("Update a pet user") {
    val url = "https://petstore.swagger.io/v2/user/rvallejo"
    val body = "{\n\t\"id\": 777," +
      "\n\t\"username\": \"rvallejo\"," +
      "\n\t\"firstName\": \"Carlos\"," +
      "\n\t\"lastName\": \"Saurez\"," +
      "\n\t\"email\": \"email@email.com\"," +
      "\n\t\"password\": \"password234\"," +
      "\n\t\"phone\": \"7698123456\"," +
      "\n\t\"userStatus\": 0\n}"

    `given`()
      .body(body)
      .contentType(ContentType.JSON)
      .when()
      .put(url)
      .`then`()
      .statusCode(HttpStatus.SC_OK)

  }
}
