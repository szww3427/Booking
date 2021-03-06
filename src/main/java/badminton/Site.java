package badminton;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Site {
    private static final int HOURS_IN_A_DAY = 24;
    private final Map<LocalDate, boolean[]> bookedHoursEachDay = new HashMap<>();
    final Set<Booking> bookings = new HashSet<>();
    final Set<Booking> canceledBookings = new HashSet<>();

    boolean canBook(LocalDate day, int startHour, int endHour) {
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

    void book(String user, LocalDate day, int startHour, int endHour) {
        for (int hour = startHour; hour < endHour; hour++) {
            setBooked(day, hour);
        }
        bookings.add(new Booking(user, day, startHour, endHour));
    }

    // 含违约金
    int total(UnitPrice unitPrice) {
        int total = 0;

        for (Booking booking : bookings) {
            total += booking.subtotal(unitPrice);
        }
        for (Booking booking : canceledBookings) {
            total += booking.indemnitySubtotal(unitPrice);
        }

        return total;
    }

    boolean isBooked(LocalDate day, int hour) {
        //noinspection SimplifiableIfStatement
        if (!bookedHoursEachDay.containsKey(day)) {
            return false;
        }

        return bookedHoursEachDay.get(day)[hour];
    }

    private void setBooked(LocalDate day, int hour) {
        if (!bookedHoursEachDay.containsKey(day)) {
            bookedHoursEachDay.put(day, new boolean[HOURS_IN_A_DAY]);
        }

        bookedHoursEachDay.get(day)[hour] = true;
    }

    public boolean canCancel(String user, LocalDate day, int startHour, int endHour) {
        return bookings.contains(new Booking(user, day, startHour, endHour));
    }

    public void cancel(String user, LocalDate day, int startHour, int endHour) {
        for (int hour = startHour; hour < endHour; hour++) {
            setNotBooked(day, hour);
        }
        bookings.remove(new Booking(user, day, startHour, endHour));
        canceledBookings.add(new Booking(user, day, startHour, endHour));
    }

    private void setNotBooked(LocalDate day, int hour) {
        if (!bookedHoursEachDay.containsKey(day)) {
            throw new RuntimeException("invalid day: not booked");
        }

        bookedHoursEachDay.get(day)[hour] = false;
    }
}
