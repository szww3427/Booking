package badminton;

public class DefaultUnitPrice extends UnitPrice {
    public DefaultUnitPrice() {
        addWeekdayPrice(9, 12, 30);
        addWeekdayPrice(12, 18, 50);
        addWeekdayPrice(18, 20, 80);
        addWeekdayPrice(20, 22, 60);

        addWeekendPrice(9, 12, 40);
        addWeekendPrice(12, 18, 50);
        addWeekendPrice(18, 22, 60);
    }
}
