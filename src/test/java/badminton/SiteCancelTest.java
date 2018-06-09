package badminton;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SiteCancelTest {
    @Test
    public void given_no_booking_exists_when_cancelling_then_refuses() {
        Site site = new Site();
        String user = "U123";
        LocalDate date = LocalDate.of(2017, 8, 2);
        int startHour = 13;
        int endHour = 17;

        boolean accepted = site.canCancel(user, date, startHour, endHour);

        assertFalse(accepted);
    }

    @Test
    public void when_cancelling_then_mark_the_hours_not_booked() {
        Site site = new Site();
        String user = "U123";
        LocalDate date = LocalDate.of(2017, 8, 2);
        int startHour = 13;
        int endHour = 17;
        site.book(user, date, startHour, endHour);

        site.cancel(user, date, startHour, endHour);

        for (int hour = startHour; hour < endHour; hour++) {
            assertFalse(site.isBooked(date, hour));
        }
    }

    @Test
    public void given_cancelled_interval_and_user_same_as_existing_when_cancelling_then_accepts() {
        Site site = new Site();
        String user = "U123";
        LocalDate date = LocalDate.of(2017, 8, 2);
        int startHour = 13;
        int endHour = 17;
        site.book(user, date, startHour, endHour);

        boolean accepted = site.canCancel(user, date, startHour, endHour);

        assertTrue(accepted);
    }

    @Test
    public void given_user_of_cancelled_booking_different_from_existing_when_cancelling_then_refuses(){
        Site site = new Site();
        String user = "U123";
        String anotherUser = "U124";
        LocalDate date = LocalDate.of(2017, 8, 2);
        int startHour = 13;
        int endHour = 17;
        site.book(user, date, startHour, endHour);

        boolean accepted = site.canCancel(anotherUser, date, startHour, endHour);

        assertFalse(accepted);
    }

    @Test
    public void given_cancelled_interval_not_intersects_with_existing_when_cancelling_then_refuses() {
        Site site = new Site();
        String user = "U123";
        LocalDate date = LocalDate.of(2017, 8, 2);
        int startHour = 13;
        int endHour1 = 17;
        int endHour2 = 20;
        site.book(user, date, startHour, endHour1);

        boolean accepted = site.canCancel(user, date, endHour1, endHour2);

        assertFalse(accepted);
    }

    @Test
    public void given_cancelled_interval_partly_intersects_with_existing_when_cancelling_then_refuses() {
        Site site = new Site();
        String user = "U123";
        LocalDate date = LocalDate.of(2017, 8, 2);
        int startHour = 13;
        int endHour = 17;
        site.book(user, date, startHour, endHour);

        assertFalse(site.canCancel(user, date, startHour - 1, endHour - 1));
        assertFalse(site.canCancel(user, date, startHour + 1, endHour + 1));
    }

    @Test
    public void given_cancelled_interval_equal_to_the_concat_of_another_two_existing_when_cancelling_then_refuses() {
        Site site = new Site();
        String user = "U123";
        LocalDate date = LocalDate.of(2017, 8, 2);
        int startHour1 = 13;
        int endHour1 = 17;
        int endHour2 = 20;
        site.book(user, date, startHour1, endHour1);
        site.book(user, date, endHour1, endHour2);

        boolean accepted = site.canCancel(user, date, startHour1, endHour2);

        assertFalse(accepted);
    }


}
