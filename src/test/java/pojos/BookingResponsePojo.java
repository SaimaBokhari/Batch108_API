package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponsePojo {
    private Integer bookingId;
    private BookingPojo booking;

    public BookingResponsePojo(Integer bookingid, BookingPojo booking) {
        this.bookingId = bookingid;
        this.booking = booking;
    }

    public BookingResponsePojo() {
    }

    public Integer getBookingid() {
        return bookingId;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingId = bookingid;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingResponsePojo{" +
                "bookingid=" + bookingId +
                ", booking=" + booking +
                '}';
    }
}