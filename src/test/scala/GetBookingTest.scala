import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import io.restassured.RestAssured.`given`
import model.BookingRequest
import org.apache.http.HttpStatus
import org.hamcrest.Matchers._
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.{be, endWith, equal, startWith}
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class GetBookingTest extends AnyFunSuite with BaseConfiguration with BeforeAndAfterAll {

  override protected def beforeAll(): Unit = setupContext("booking")

  test("Get booking ids") {
    val response = `given`().when().get().`then`()

    response.statusCode(HttpStatus.SC_OK)
    response.body("bookingid", everyItem(notNullValue()))
    response.body("size()", greaterThan(Integer.valueOf(1)))
  }

  test("Get a booking by id (validate JSON)") {
    val response = `given`().when().get("/4370").`then`()

    response.statusCode(HttpStatus.SC_OK)

    response.body("firstname", equalTo("Alfonso"))
    response.body("lastname", equalToIgnoringCase("Vallejo"))
    response.body("totalprice", greaterThanOrEqualTo(Integer.valueOf(111)))
    response.body("depositpaid", equalTo(true))
    response.body("bookingdates", hasKey("checkin"))
    response.body("bookingdates.checkin", startsWith("2021"))
    response.body("bookingdates.checkout", endsWith("10"))
    response.body("additionalneeds", notNullValue())
  }

  test("Get a booking by id (validate serialized)") {
    val response = `given`().when().get("/4370")
    val bookingInfo = response.as(classOf[BookingRequest])

    bookingInfo.firstname should equal ("Alfonso")
    bookingInfo.lastname shouldEqual "Vallejo"
    bookingInfo.totalprice should be >= 111
    bookingInfo.depositpaid should be (true)
    bookingInfo.bookingdates.checkin should startWith ("2021")
    bookingInfo.bookingdates.checkout should endWith ("10")
    bookingInfo.additionalneeds shouldNot be ("")
  }

  test("Get a booking by id (validate serialized 2)") {
    val response = `given`().when().get("/4370")
    val mapper = JsonMapper.builder().addModule(DefaultScalaModule).build()
    val bookingInfo = mapper.readValue(response.asString(), classOf[BookingRequest])

    bookingInfo.firstname should equal ("Alfonso")
    bookingInfo.lastname shouldEqual "Vallejo"
    bookingInfo.totalprice should be >= 111
    bookingInfo.depositpaid should be (true)
    bookingInfo.bookingdates.checkin should startWith ("2021")
    bookingInfo.bookingdates.checkout should endWith ("10")
    bookingInfo.additionalneeds shouldNot be ("")
  }

  test("Get booking ids filtered by date") {
    `given`()
      .queryParam("checkin", "2016-01-01")
      .queryParam("checkout", "2021-12-31")
      .when()
      .get()
      .`then`()
      .statusCode(HttpStatus.SC_OK)
  }

  test("Get booking ids filtered by name") {
    `given`()
      .queryParams("firstname", "Sally", "lastname", "Brown")
      .when()
      .get()
      .`then`()
      .statusCode(HttpStatus.SC_OK)
  }

}
