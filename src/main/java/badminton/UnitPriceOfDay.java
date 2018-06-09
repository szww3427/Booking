package badminton;

//单位：元
class UnitPriceOfDay {
    private static final int HOURS_IN_A_DAY = 24;
    private static final int OUT_OF_SERVICE = -1;
    private final int[] unitPrice;// 可取-1，代表不营业

    UnitPriceOfDay() {
        unitPrice = new int[HOURS_IN_A_DAY];
        for (int hour = 0; hour < HOURS_IN_A_DAY; hour++) {
            unitPrice[hour] = -1;
        }
    }

    void addPrice(int startHour, int endHour, int price) {
        for (int hour = startHour; hour < endHour; hour++) {
            unitPrice[hour] = price;
        }
    }

    boolean onService(int hour) {
        return unitPrice[hour] != OUT_OF_SERVICE;
    }

    int get(int hour) {
        if (!onService(hour)) {
            throw new RuntimeException("out of service");
        }

        return unitPrice[hour];
    }
}
