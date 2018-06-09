package badminton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// TODO 异常处理
public class RequestParser {
    public static Request parse(String input) throws RequestFormatException {
        String[] parts = input.split(" ");
        if (parts.length != 4 && parts.length != 5) {
            throw new RequestFormatException();
        }

        String user = parts[0];
        LocalDate date = LocalDate.parse(parts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        HourRange range = parseHourRange(parts[2]);
        String site = parts[3];

        boolean isBooking = parts.length == 4;

        return new Request(
                new Booking(user, date, range.start, range.end),
                site, isBooking
        );
    }

    // @param hourRange 形如：20:00~22:00
    private static HourRange parseHourRange(String hourRange) {
        String[] parts = hourRange.split("~");

        int startHour = Integer.parseInt(parts[0].substring(0, 2));
        int endHour = Integer.parseInt(parts[1].substring(0, 2));

        return new HourRange(startHour, endHour);
    }

    private static class HourRange {
        private int start;
        private int end;

        HourRange(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
