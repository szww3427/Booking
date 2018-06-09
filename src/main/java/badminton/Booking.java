package badminton;

import java.time.LocalDate;
import java.util.Objects;

class Booking {
    String user;
    LocalDate date;
    int startHour;
    int endHour;

    Booking(String user, LocalDate date, int startHour, int endHour) {
        this.user = user;
        this.date = date;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    int subtotal(UnitPrice unitPrice) {
        int sum = 0;
        for (int hour = startHour; hour < endHour; hour++) {
            sum += unitPrice.get(date.getDayOfWeek().getValue(), hour);
        }

        return sum;
    }

    int indemnitySubtotal(UnitPrice unitPrice) {
        int sum = 0;
        for (int hour = startHour; hour < endHour; hour++) {
            sum += unitPrice.getIndemnity(date.getDayOfWeek().getValue(), hour);
        }

        return sum;
    }


    String desc() {
        return new StringBuilder()
                .append(date.toString())
                .append(" ")
                .append(startHour + ":00")
                .append("~")
                .append(endHour + ":00")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return startHour == booking.startHour &&
                endHour == booking.endHour &&
                Objects.equals(user, booking.user) &&
                Objects.equals(date, booking.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, date, startHour, endHour);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "user='" + user + '\'' +
                ", date=" + date +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                '}';
    }
}
