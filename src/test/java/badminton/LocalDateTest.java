package badminton;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class LocalDateTest {
    @Test
    public void testGet() {
        LocalDate date = LocalDate.parse("2018-06-09", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assertEquals(2018, date.getYear());
        assertEquals(6, date.getMonth().getValue());
        assertEquals(9, date.getDayOfMonth());
        assertEquals(6, date.getDayOfWeek().getValue());
    }

    @Test
    public void testEquals() {
        LocalDate date1 = LocalDate.parse("2018-06-09", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date2 = LocalDate.parse("2018-06-09", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        assertEquals(date1, date2);
    }
}
