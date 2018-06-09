package badminton;

import java.util.HashMap;
import java.util.Map;

// TODO 异常处理
public class Stadium {
    private final UnitPrice unitPrice;
    private final Map<String, Site> sites;

    public Stadium(UnitPrice unitPrice, String[] sites) {
        this.unitPrice = unitPrice;
        this.sites = new HashMap<>();
        for (String site : sites) {
            this.sites.put(site, new Site());
        }
    }

    public void processRequest(String requestStr) {
        Request request;
        try {
            request = RequestParser.parse(requestStr);
        }catch (RequestFormatException e){
            System.out.println("Error: the booking is invalid!");
            return;
        }

        if(! sites.containsKey(request.site)){
            System.out.println("Error: site not exists!");
            return;
        }
        Site bookedSite = sites.get(request.site);

        if (request.isBooking) {
            if(!bookedSite.canBook(
                    request.booking.date,
                    request.booking.startHour,
                    request.booking.endHour)){
                System.out.println("Error: the booking conflicts with existing bookings!");
                return;
            }

            bookedSite.book(
                    request.booking.user,
                    request.booking.date,
                    request.booking.startHour,
                    request.booking.endHour);
            System.out.println("Success: the booking is accepted!");

        } else {
            if(!bookedSite.canCancel(
                    request.booking.user,
                    request.booking.date,
                    request.booking.startHour,
                    request.booking.endHour)){
                System.out.println("Error: the booking being cancelled does not exist!");
                return;
            }

            bookedSite.cancel(
                    request.booking.user,
                    request.booking.date,
                    request.booking.startHour,
                    request.booking.endHour);
            System.out.println("Success: the booking is cancelled!");
        }
    }

    public void printTotal() {
        // TODO 顺序
        System.out.println("收入汇总");
        System.out.println("---");
        for (Map.Entry<String, Site> entry : sites.entrySet()) {
            printSiteTotal(entry.getKey(), entry.getValue());
            System.out.println();
        }

        System.out.println("---");
        System.out.println("总计：" + total());
    }

    private void printSiteTotal(String siteName, Site site) {
        System.out.println("场地：" + siteName);

        for (Booking booking : site.bookings) {
            System.out.println(booking.desc() + " " + booking.subtotal(unitPrice) + "元");
        }
        for (Booking booking : site.canceledBookings) {
            System.out.println(booking.desc() + " 违约金 " + booking.indemnitySubtotal(unitPrice) + "元");
        }

        System.out.println("小计：" + site.total(unitPrice) + "元");
    }

    private int total() {
        int total = 0;
        for (Site site : sites.values()) {
            total += site.total(unitPrice);
        }

        return total;
    }
}
