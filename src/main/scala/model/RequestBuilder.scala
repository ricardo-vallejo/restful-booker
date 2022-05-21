package model

trait RequestBuilder {

  def withFirstName(firstName: String): RequestBuilder
  def withLastName(lastName: String): RequestBuilder
  def withTotalPrice(totalPrice: Int): RequestBuilder
  def withDepositPaid(depositPaid: Boolean): RequestBuilder
  def withBookingDates(bookingDates: BookingDates): RequestBuilder
  def withAdditionalNeeds(additionalNeeds: String): RequestBuilder

  def build(): BookingRequest

}

class BookingBuilder extends RequestBuilder {

  private var firstName = ""
  private var lastName = ""
  private var totalPrice = 0
  private var depositPaid = true
  private var bookingDates = BookingDates("", "")
  private var additionalNotes = ""

  override def withFirstName(firstName: String): RequestBuilder = {
    this.firstName = firstName
    this
  }

  override def withLastName(lastName: String): RequestBuilder = {
    this.lastName = lastName
    this
  }

  override def withTotalPrice(totalPrice: Int): RequestBuilder = {
    this.totalPrice = totalPrice
    this
  }

  override def withDepositPaid(depositPaid: Boolean): RequestBuilder = {
    this.depositPaid = depositPaid
    this
  }

  override def withBookingDates(bookingDates: BookingDates): RequestBuilder = {
    this.bookingDates = bookingDates
    this
  }

  override def withAdditionalNeeds(additionalNeeds: String): RequestBuilder = {
    this.additionalNotes = additionalNeeds
    this
  }

  override def build(): BookingRequest = {
    BookingRequest(firstName, lastName, totalPrice, depositPaid, bookingDates, additionalNotes)
  }
}
