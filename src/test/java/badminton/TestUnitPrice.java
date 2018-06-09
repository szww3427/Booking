package badminton;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestUnitPrice {
    @Test
    public void unit_price_on_weekday() {
        UnitPrice unitPrice = new UnitPrice();
        unitPrice.addWeekdayPrice(9,12,30);
        unitPrice.addWeekdayPrice(12,18,50);
        unitPrice.addWeekdayPrice(18,20,80);
        unitPrice.addWeekdayPrice(20,22,60);

        assertFalse(unitPrice.onService(1, 8));
        assertEquals(30, unitPrice.get(1, 9));
        assertEquals(30, unitPrice.get(1, 11));
        assertEquals(50, unitPrice.get(1, 12));
        assertEquals(50, unitPrice.get(1, 17));
        assertEquals(80, unitPrice.get(1, 18));
        assertEquals(80, unitPrice.get(1, 19));
        assertEquals(60, unitPrice.get(1, 20));
        assertEquals(60, unitPrice.get(1, 21));
        assertFalse(unitPrice.onService(1, 22));
    }

    @Test
    public void unit_price_on_weekend() {
        UnitPrice unitPrice = new UnitPrice();
        unitPrice.addWeekendPrice(9,12,40);
        unitPrice.addWeekendPrice(12,18,50);
        unitPrice.addWeekendPrice(18,22,60);

        assertFalse(unitPrice.onService(6, 8));
        assertEquals(40, unitPrice.get(6, 9));
        assertEquals(40, unitPrice.get(6, 11));
        assertEquals(50, unitPrice.get(6, 12));
        assertEquals(50, unitPrice.get(6, 17));
        assertEquals(60, unitPrice.get(6, 18));
        assertEquals(60, unitPrice.get(6, 21));
        assertFalse(unitPrice.onService(6, 22));
    }

}
