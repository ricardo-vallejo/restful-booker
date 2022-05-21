import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import faker._
import io.restassured.RestAssured.`given`
import io.restassured.http.ContentType
import model.{BookingDataBuilder, BookingDates, BookingRequest}
import org.apache.http.HttpStatus
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite

import java.time.format.DateTimeFormatter

class CreateBookingTest extends AnyFunSuite with BaseConfiguration with BeforeAndAfterAll{

  override protected def beforeAll(): Unit = {
    setupContext("booking")
  }

  test("Create a new booking") {
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

    `given`()
      .body(newBooking)
      .contentType(ContentType.JSON)
      .when()
      .post()
      .`then`()
      .statusCode(HttpStatus.SC_OK)
  }

  test("Create a new booking (data builder)") {
    val booking = BookingDataBuilder.defaultBooking()
    val mapper = JsonMapper.builder().addModule(DefaultScalaModule).build()

    mapper.writeValueAsString(booking)

    given
      .body(booking)
      .contentType(ContentType.JSON)
      .when()
      .post()
      .`then`()
      .statusCode(HttpStatus.SC_OK)
  }

  test("Create a new booking (data builder 2)") {
    val defaultBooking = BookingDataBuilder.defaultBooking()
    val specificBooking = BookingDataBuilder.spainBooking()
    val mapper = JsonMapper.builder().addModule(DefaultScalaModule).build()

    mapper.writeValueAsString(defaultBooking)

    given
      .body(defaultBooking)
      .contentType(ContentType.JSON)
      .when()
      .post()
      .`then`()
      .statusCode(HttpStatus.SC_OK)

    mapper.writeValueAsString(specificBooking)

    `given`()
      .body(specificBooking)
      .contentType(ContentType.JSON)
      .when()
      .post()
      .`then`()
      .statusCode(HttpStatus.SC_OK)
  }
}
