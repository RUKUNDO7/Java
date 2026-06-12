import java.util.*;

public class Main {
    public static void main (String[] args) {
        FullTimeEmployee employee1 = new FullTimeEmployee("Alex",700000.00);
        FreelancerEmployee employee2 = new FreelancerEmployee("Bill",4, 100000);

        List<Payable> pays = new ArrayList<>();
        pays.add(employee1);
        pays.add(employee2);

        for (Payable e : pays) {
            System.out.println(e.getName() + " " + e.calculatePay());
        }
    }
}