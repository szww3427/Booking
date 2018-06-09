package badminton;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Site {
    private static final int HOURS_IN_A_DAY = 24;
    private final Map<Day, boolean[]> bookedHoursEachDay = new HashMap<>();
    private final Set<Interval> bookedIntervals = new HashSet<>();

    public void book(Day day, int startHour, int endHour) {
        for (int hour = startHour; hour < endHour; hour++) {
            setBooked(day, hour);
        }
        bookedIntervals.add(new Interval(day, startHour, endHour));
    }

    //TODO: add user id as param
    public boolean canBook(Day day, int startHour, int endHour) {
        if (!bookedHoursEachDay.containsKey(day)) {
            return true;
        }

        for (int hour = startHour; hour < endHour; hour++) {
            if (isBooked(day, hour)) {
                return false;
            }
        }

        return true;
    }

    boolean isBooked(Day day, int hour) {
        //noinspection SimplifiableIfStatement
        if (!bookedHoursEachDay.containsKey(day)) {
            return false;
        }

        return bookedHoursEachDay.get(day)[hour];
    }

    private void setBooked(Day day, int hour) {
        if (!bookedHoursEachDay.containsKey(day)) {
            bookedHoursEachDay.put(day, new boolean[HOURS_IN_A_DAY]);
        }

        bookedHoursEachDay.get(day)[hour] = true;
    }

    //TODO: add user id as param
    public boolean canCancel(Day day, int startHour, int endHour) {
        if (!bookedIntervals.contains(new Interval(day, startHour, endHour))) {
            return false;
        }

        return true;
    }

    public void cancel(Day day, int startHour, int endHour) {
        for (int hour = startHour; hour < endHour; hour++) {
            setNotBooked(day, hour);
        }
        bookedIntervals.remove(new Interval(day, startHour, endHour));
    }

    private void setNotBooked(Day day, int hour) {
        if (!bookedHoursEachDay.containsKey(day)) {
            throw new RuntimeException("invalid day: not booked");
        }

        bookedHoursEachDay.get(day)[hour] = false;
    }
}
