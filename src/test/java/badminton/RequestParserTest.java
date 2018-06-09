package badminton;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    @Test
    public void valid_booking_input() {
        String input = "U123 2016-06-02 20:00~22:00 A";

        Request request = RequestParser.parse(input);

        assertEquals(new Request(new Booking(
                        "U123",
                        LocalDate.of(2016, 6, 2),
                        20, 22),
                        "A",
                        true),
                request);
    }

    @Test
    public void valid_cancelling_input() {
        String input = "U123 2016-06-02 20:00~22:00 A C";

        Request request = RequestParser.parse(input);

        assertEquals(new Request(new Booking(
                        "U123",
                        LocalDate.of(2016, 6, 2),
                        20, 22),
                        "A",
                        false),
                request);
    }


}