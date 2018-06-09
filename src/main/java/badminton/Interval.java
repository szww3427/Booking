package badminton;

import java.util.Objects;

class Interval {
    private Day day;
    private int startHour;
    private int endHour;

    Interval(Day day, int startHour, int endHour) {
        this.day = day;
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
                Objects.equals(day, interval.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, startHour, endHour);
    }
}
