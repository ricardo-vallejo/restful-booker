package model

/**
 * Booking Response Case Class
 * @param bookingid ID for newly created booking
 * @param booking Booking object
 */
case class BookingResponse(bookingid: Int, booking: BookingRequestBuilder)