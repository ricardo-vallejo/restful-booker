package model

import faker._

import java.time.format.DateTimeFormatter

/**  */
case class BookingRequestBuilder(
                                  firstname: String = Faker.default.firstName(),
                                  lastname: String = Faker.default.lastName(),
                                  totalprice: Int = 0,
                                  depositpaid: Boolean = true,
                                  bookingdates: BookingDates = BookingDates(Faker.default.nowLocalDateTime().minusDays(5L).format(DateTimeFormatter.ISO_DATE),
                                    Faker.default.nowLocalDateTime().format(DateTimeFormatter.ISO_DATE)),
                                  additionalneeds: String = Faker.default.buzzWord()
                                )

/**  */
object BookingRequestBuilder {

  /**  */
  def spainBooking(firstname: String = Faker.es.firstName(),
            lastname: String = Faker.es.lastName(),
            totalprice: Int = 0,
            depositpaid: Boolean = true,
            bookingdates: BookingDates = BookingDates(Faker.es.nowLocalDateTime().minusDays(5L).format(DateTimeFormatter.ISO_DATE),
              Faker.es.nowLocalDateTime().format(DateTimeFormatter.ISO_DATE)),
            additionalneeds: String = Faker.es.buzzWord()): BookingRequestBuilder = {
    BookingRequestBuilder(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds)
  }

  /**  */
  def italianBooking(firstname: String = Faker.it.firstName(),
            lastname: String = Faker.it.lastName(),
            totalprice: Int = 0,
            depositpaid: Boolean = true,
            bookingdates: BookingDates = BookingDates(Faker.it.nowLocalDateTime().minusDays(5L).format(DateTimeFormatter.ISO_DATE),
              Faker.it.nowLocalDateTime().format(DateTimeFormatter.ISO_DATE)),
            additionalneeds: String = Faker.it.buzzWord()): BookingRequestBuilder = {
    BookingRequestBuilder(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds)
  }
}