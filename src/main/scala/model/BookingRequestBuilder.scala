package model

import faker._

import java.time.format.DateTimeFormatter

/**
 * Booking Request Case Class
 * @param firstname Firstname for the guest who made the booking
 * @param lastname Lastname for the guest who made the booking
 * @param totalprice The total price for the booking
 * @param depositpaid Whether the deposit has been paid or not
 * @param bookingdates Checkin and Checkout dates
 * @param additionalneeds Any other needs the guest has
 */
case class BookingRequestBuilder(
                                  firstname: String = Faker.default.firstName(),
                                  lastname: String = Faker.default.lastName(),
                                  totalprice: Int = 0,
                                  depositpaid: Boolean = true,
                                  bookingdates: BookingDates = BookingDates(Faker.default.nowLocalDateTime().minusDays(5L).format(DateTimeFormatter.ISO_DATE),
                                    Faker.default.nowLocalDateTime().format(DateTimeFormatter.ISO_DATE)),
                                  additionalneeds: String = Faker.default.buzzWord()
                                )

/**
 * Booking Request Builder
 *
 * Singleton object to create a builder for different object values
 */
object BookingRequestBuilder {

  /**
   * Booking Request Data Builder
   * @param firstname Firstname for the guest who made the booking
   * @param lastname Lastname for the guest who made the booking
   * @param totalprice The total price for the booking
   * @param depositpaid Whether the deposit has been paid or not
   * @param bookingdates Checkin and Checkout dates
   * @param additionalneeds Any other needs the guest has
   * @return BookingRequestBuilder object with specific values
   */
  def spainBooking(firstname: String = Faker.es.firstName(),
            lastname: String = Faker.es.lastName(),
            totalprice: Int = 0,
            depositpaid: Boolean = true,
            bookingdates: BookingDates = BookingDates(Faker.es.nowLocalDateTime().minusDays(5L).format(DateTimeFormatter.ISO_DATE),
              Faker.es.nowLocalDateTime().format(DateTimeFormatter.ISO_DATE)),
            additionalneeds: String = Faker.es.buzzWord()): BookingRequestBuilder = {
    BookingRequestBuilder(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds)
  }

  /**
   * Booking Request Data Builder
   * @param firstname Firstname for the guest who made the booking
   * @param lastname Lastname for the guest who made the booking
   * @param totalprice The total price for the booking
   * @param depositpaid Whether the deposit has been paid or not
   * @param bookingdates Checkin and Checkout dates
   * @param additionalneeds Any other needs the guest has
   * @return BookingRequestBuilder object with specific values
   */
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