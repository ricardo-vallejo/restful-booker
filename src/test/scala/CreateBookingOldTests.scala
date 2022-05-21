import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import faker._
import io.restassured.RestAssured.`given`
import io.restassured.http.ContentType
import model.{BookingDates, BookingRequestBuilder, BookingResponse}
import org.apache.http.HttpStatus
import org.hamcrest.Matchers._
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers._
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

import java.time.format.DateTimeFormatter

class CreateBookingOldTests extends AnyFunSuite with BaseConfiguration with BeforeAndAfterAll{

  /** BeforeAll Method
   * We use this method to configure or prepare values
   * that are going to be used in the test */
  override protected def beforeAll(): Unit = {
    setupContext("booking")
    val mapper = JsonMapper.builder().addModule(DefaultScalaModule).build()
  }

  test("Get booking ids") {
    val response = `given`.when().get().`then`()

    response.statusCode(HttpStatus.SC_OK)
    response.body("bookingid", everyItem(notNullValue()))

    /** You should use Integer.valueOf() constructor to compare a value in Scala. */
    response.body("size()", greaterThan(Integer.valueOf(1)))
  }

  test("Get a booking by id (validate JSON)") {

    val response = `given`.when().get("/1").`then`()

    response.statusCode(HttpStatus.SC_OK)

    /** You can check each element of the body using the hamecrest matchers */

    response.body("firstname", equalTo("Sally"))
    response.body("lastname", equalToIgnoringCase("smith"))
    response.body("totalprice", greaterThanOrEqualTo(Integer.valueOf(111)))
    response.body("depositpaid", equalTo(false))
    response.body("bookingdates", hasKey("checkin"))
    response.body("bookingdates.checkin", startsWith("2021"))
    response.body("bookingdates.checkout", endsWith("27"))
    response.body("additionalneeds", notNullValue())

  }

  test("Get a booking by id (validate serialized)") {

    val response = `given`.when().get("/1")

    val bookingInfo = response.as(classOf[BookingRequestBuilder])

    /** You can use mapper.readValue() to convert a string JSON response to an Scala Object
     * mapper.readValue(response.asString(), classOf[BookingRequestBuilder]) */

    /** You can check the object using the scalaTest matchers */

    bookingInfo.firstname should equal ("Susan")
    bookingInfo.lastname shouldEqual "Wilson"
    bookingInfo.totalprice should be >= 112
    bookingInfo.depositpaid should be (true)
    bookingInfo.bookingdates.checkin should startWith ("2021")
    bookingInfo.bookingdates.checkout should endWith ("28")
    //bookingInfo.additionalneeds shouldNot be (empty)

  }

  test("Create a new booking") {

    val mapper = JsonMapper.builder().addModule(DefaultScalaModule).build()

    val newBooking = BookingRequestBuilder(Faker.default.firstName(),
      Faker.default.lastName(),
      110, depositpaid = true,
      BookingDates(
        Faker.default.nowLocalDateTime().minusDays(5L).format(DateTimeFormatter.ISO_DATE),
        Faker.default.nowLocalDateTime().format(DateTimeFormatter.ISO_DATE)),
      Faker.default.buzzWord())

    mapper.writeValueAsString(newBooking)

    val response = given
      .body(newBooking)
      .contentType(ContentType.JSON)
      .when()
      .post()
      .`then`()
      .statusCode(HttpStatus.SC_OK)
  }

  test("Create a new booking with json") {

    val mapper = JsonMapper.builder().addModule(DefaultScalaModule).build()

    val newBooking = BookingRequestBuilder()
    val italianBooking = BookingRequestBuilder.italianBooking()
    val spainBooking = BookingRequestBuilder.spainBooking()

    mapper.writeValueAsString(newBooking)
    val response = given
      .body(newBooking)
      .contentType(ContentType.JSON)
      .when()
      .log().all()
      .post()

    val jsonResponse = response.as(classOf[BookingResponse])

    println(jsonResponse)

    mapper.writeValueAsString(italianBooking)
    val responseIt = given
      .body(italianBooking)
      .contentType(ContentType.JSON)
      .when()
      .log().all()
      .post()

    val jsonResponseIt = responseIt.as(classOf[BookingResponse])

    println(jsonResponseIt)

    mapper.writeValueAsString(spainBooking)
    val responseSp = given
      .body(spainBooking)
      .contentType(ContentType.JSON)
      .when()
      .log().all()
      .post()

    val jsonResponseSp = responseSp.as(classOf[BookingResponse])

    println(jsonResponseSp)
  }

}
