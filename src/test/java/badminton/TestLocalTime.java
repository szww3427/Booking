package badminton;

import org.junit.Test;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class TestLocalTime {
    @Test
    public void test() {
        LocalDate date = LocalDate.parse("2018-06-09", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assertEquals(2018, date.getYear());
        assertEquals(6, date.getMonth().getValue());
        assertEquals(9, date.getDayOfMonth());
        assertEquals(6, date.getDayOfWeek().getValue());
    }
}
