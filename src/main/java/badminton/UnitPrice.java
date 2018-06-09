package badminton;

//单位：元
public class UnitPrice {
    private final UnitPriceOfDay weekdayPrice;
    private final UnitPriceOfDay weekendPrice;

    public UnitPrice() {
        weekdayPrice = new UnitPriceOfDay();
        weekendPrice = new UnitPriceOfDay();
    }

    public void addWeekdayPrice(int startHour, int endHour, int price) {
        weekdayPrice.addPrice(startHour, endHour, price);
    }

    public void addWeekendPrice(int startHour, int endHour, int price) {
        weekendPrice.addPrice(startHour, endHour, price);
    }

    public boolean onService(int dayOfWeek, int hour) {
        return isWeekday(dayOfWeek) ?
                weekdayPrice.onService(hour) :
                weekendPrice.onService(hour);
    }

    public int get(int dayOfWeek, int hour) {
        return isWeekday(dayOfWeek) ?
                weekdayPrice.get(hour) :
                weekendPrice.get(hour);
    }

    private boolean isWeekday(int dayOfWeek) {
        if (dayOfWeek < 1 || dayOfWeek > 7) {
            throw new IllegalArgumentException("invalid day of week");
        }

        return dayOfWeek <= 5;
    }
}
