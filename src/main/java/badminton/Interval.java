package badminton;

import java.time.LocalDate;
import java.util.Objects;

class Interval {
    private LocalDate date;
    private int startHour;
    private int endHour;

    Interval(LocalDate date, int startHour, int endHour) {
        this.date = date;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return startHour == interval.startHour &&
                endHour == interval.endHour &&
                Objects.equals(date, interval.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, startHour, endHour);
    }

    public int subtotal(UnitPrice unitPrice) {
        int sum = 0;
        for (int hour = startHour; hour < endHour; hour++) {
            sum += unitPrice.get(date.getDayOfWeek().getValue(), hour);
        }

        return sum;
    }
}
