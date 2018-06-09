package badminton;

import java.util.HashMap;
import java.util.Map;

public class Site {
    private static final int HOURS_IN_A_DAY = 24;
    private final Map<Day, boolean[]> isBookedEachDay = new HashMap<>();

    public void book(Day day, int startHour, int endHour) {
        for (int hour = startHour; hour < endHour; hour++) {
            setBooked(day, hour);
        }
    }

    public boolean canBook(Day day, int startHour, int endHour) {
        if (!isBookedEachDay.containsKey(day)) {
            return true;
        }

        for(int hour = startHour;hour<endHour;hour++){
            if (isBooked(day,hour)){
                return false;
            }
        }

        return true;
    }

    boolean isBooked(Day day, int hour) {
        //noinspection SimplifiableIfStatement
        if (!isBookedEachDay.containsKey(day)) {
            return false;
        }

        return isBookedEachDay.get(day)[hour];
    }

    private void setBooked(Day day, int hour) {
        if (!isBookedEachDay.containsKey(day)) {
            isBookedEachDay.put(day, new boolean[HOURS_IN_A_DAY]);
        }

        isBookedEachDay.get(day)[hour] = true;
    }
}
