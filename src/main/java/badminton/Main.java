package badminton;

public class Main {

    public static void main(String[] args) {
        Stadium stadium = new Stadium(
                new DefaultUnitPrice(),
                new String[]{"A", "B", "C", "D"});

        stadium.processRequest("U123 2016-06-02 09:00~10:00 A");
        stadium.processRequest("U123 2016-06-02 09:00~10:00 A C");
        stadium.processRequest("U123 2016-06-02 20:00~22:00 A");
        stadium.processRequest("U123 2016-06-02 20:00~22:00 B");

        stadium.processRequest("U123 2016-06-02 20:00~22:00 F"); //invalid: no site
        stadium.processRequest("U123 2016-06-02 20:00~22:00"); //invalid: format
        stadium.processRequest("U123 2016-06-02 20:00~22:00 A"); //invalid: conflict
        stadium.processRequest("U123 2017-06-02 20:00~22:00 A C"); //invalid: not exist


        stadium.printTotal();
    }
}
