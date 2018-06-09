package badminton;

import java.util.Objects;

class Request {
    final Booking booking;
    final String site;
    final boolean isBooking;//false: cancel

    Request(Booking booking, String site, boolean isBooking) {
        this.booking = booking;
        this.site = site;
        this.isBooking = isBooking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return isBooking == request.isBooking &&
                Objects.equals(booking, request.booking) &&
                Objects.equals(site, request.site);
    }

    @Override
    public String toString() {
        return "Request{" +
                "booking=" + booking +
                ", site='" + site + '\'' +
                ", isBooking=" + isBooking +
                '}';
    }
}
