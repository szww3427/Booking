package badminton;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TestSubtotal {

    @Test
    public void test_weekday_not_cancelled() {
        Interval interval = new Interval(LocalDate.of(2018, 6, 4), 9, 13);

        int subtotal = interval.subtotal(new DefaultUnitPrice());

        assertEquals(140, subtotal);
    }

    @Test
    public void test_weekend_not_cancelled() {

    }

    @Test
    public void test_weekday_cancelled() {

    }

    @Test
    public void test_weekend_cancelled() {

    }
}
