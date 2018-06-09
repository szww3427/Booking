package badminton;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SiteBookTest {
    @Test
    public void given_no_booking_exists_when_booking_then_accepts() {
        Site site = new Site();
        LocalDate date = LocalDate.of(2017, 8, 2);
        int startHour = 13;
        int endHour = 17;

        boolean accepted = site.canBook(date, startHour, endHour);

        assertTrue(accepted);
        for (int hour = startHour; hour < endHour; hour++) {
            assertFalse(site.isBooked(date, hour));
        }
    }

    @Test
    public void when_booking_then_mark_the_hours_booked() {
        Site site = new Site();
        LocalDate date = LocalDate.of(2017, 8, 2);
        int startHour = 13;
        int endHour = 17;

        site.book("unused", date, startHour, endHour);

        for (int hour = startHour; hour < endHour; hour++) {
            assertTrue(site.isBooked(date, hour));
        }
    }

    @Test
    public void given_booked_interval_different_from_existing_when_booking_then_accepts() {
        Site site = new Site();
        LocalDate date = LocalDate.of(2017, 8, 2);
        int startHour = 13;
        int endHour1 = 17;
        int endHour2 = 20;
        site.book("unused", date, startHour, endHour1);

        boolean accepted = site.canBook(date, endHour1, endHour2);

        assertTrue(accepted);
    }

    @Test
    public void given_booked_interval_same_as_existing_when_booking_then_refuses() {
        Site site = new Site();
        LocalDate date = LocalDate.of(2017, 8, 2);
        int startHour = 13;
        int endHour = 17;

        site.book("unused", date, startHour, endHour);
        boolean accepted = site.canBook(date, startHour, endHour);

        assertFalse(accepted);
    }

    @Test
    public void given_booked_interval_intersects_with_existing_when_booking_then_refuses() {
        Site site = new Site();
        LocalDate date = LocalDate.of(2017, 8, 2);
        int startHour = 13;
        int endHour = 17;

        site.book("unused", date, startHour, endHour);
        assertFalse(site.canBook(date, startHour - 1, endHour - 1));
        assertFalse(site.canBook(date, startHour + 1, endHour + 1));
    }

}
