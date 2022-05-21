package model

import faker._

import java.time.format.DateTimeFormatter

object BookingDataBuilder {
  def defaultBooking(): BookingRequest = new BookingBuilder().withFirstName(Faker.default.firstName())
    .withLastName(Faker.default.lastName())
    .withTotalPrice(0)
    .withDepositPaid(true)
    .withBookingDates(BookingDates(
      Faker.default.nowLocalDateTime().format(DateTimeFormatter.ISO_DATE),
      Faker.default.nowLocalDateTime().plusDays(5L).format(DateTimeFormatter.ISO_DATE)))
    .withAdditionalNeeds(Faker.default.buzzWord())
    .build()

  def spainBooking(): BookingRequest = new BookingBuilder().withFirstName(Faker.es.firstName())
    .withLastName(Faker.es.lastName())
    .withTotalPrice(0)
    .withDepositPaid(true)
    .withBookingDates(BookingDates(
      Faker.es.nowLocalDateTime().format(DateTimeFormatter.ISO_DATE),
      Faker.es.nowLocalDateTime().plusDays(5L).format(DateTimeFormatter.ISO_DATE)))
    .withAdditionalNeeds(Faker.es.buzzWord())
    .build()
}
