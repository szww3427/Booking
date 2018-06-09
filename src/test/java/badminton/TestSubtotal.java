package badminton;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TestSubtotal {

    @Test
    public void test_weekday() {
        // 2018年6月4日：周一
        Interval interval = new Interval(LocalDate.of(2018, 6, 4), 9, 13);

        int subtotal = interval.subtotal(new DefaultUnitPrice());

        assertEquals(140, subtotal);
    }

    @Test
    public void test_weekend() {
        // 2018年6月9日：周六
        Interval interval = new Interval(LocalDate.of(2018, 6, 9), 9, 13);

        int subtotal = interval.subtotal(new DefaultUnitPrice());

        assertEquals(170, subtotal);
    }
}
