package model

case class BookingRequest(firstname: String,
                          lastname: String,
                          totalprice: Int,
                          depositpaid: Boolean,
                          bookingdates: BookingDates,
                          additionalneeds: String)
