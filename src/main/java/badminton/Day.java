package badminton;

import java.util.Objects;

class Day {
    final int year;
    final int month; //从1到12
    final int day;   //每月从1日开始

    public Day(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day1 = (Day) o;
        return year == day1.year &&
                month == day1.month &&
                day == day1.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }
}
