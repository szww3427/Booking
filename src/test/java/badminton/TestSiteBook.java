package badminton;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestSiteBook {
    @Test
    public void given_no_booking_exists_when_booking_then_accepts() {
        Site site = new Site();
        Day day = new Day(2017, 8, 2);
        int startHour = 13;
        int endHour = 17;

        boolean accepted = site.canBook(day, startHour, endHour);

        assertTrue(accepted);
        for (int hour = startHour; hour < endHour; hour++) {
            assertFalse(site.isBooked(day, hour));
        }
    }

    @Test
    public void when_booking_then_mark_the_hours_booked() {
        Site site = new Site();
        Day day = new Day(2017, 8, 2);
        int startHour = 13;
        int endHour = 17;

        site.book(day, startHour, endHour);

        for (int hour = startHour; hour < endHour; hour++) {
            assertTrue(site.isBooked(day, hour));
        }
    }

    @Test
    public void given_booked_interval_different_from_existing_when_booking_then_accepts() {
        Site site = new Site();
        Day day = new Day(2017, 8, 2);
        int startHour = 13;
        int endHour1 = 17;
        int endHour2 = 20;
        site.book(day, startHour, endHour1);

        boolean accepted = site.canBook(day, endHour1, endHour2);

        assertTrue(accepted);
    }

    @Test
    public void given_booked_interval_same_as_existing_when_booking_then_refuses() {
        Site site = new Site();
        Day day = new Day(2017, 8, 2);
        int startHour = 13;
        int endHour = 17;

        site.book(day, startHour, endHour);
        boolean accepted = site.canBook(day, startHour, endHour);

        assertFalse(accepted);
    }

    @Test
    public void given_booked_interval_intersects_with_existing_when_booking_then_refuses() {
        Site site = new Site();
        Day day = new Day(2017, 8, 2);
        int startHour = 13;
        int endHour = 17;

        site.book(day, startHour, endHour);
        assertFalse(site.canBook(day, startHour - 1, endHour - 1));
        assertFalse(site.canBook(day, startHour + 1, endHour + 1));
    }

}
