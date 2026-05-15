package structural;

public class Phone {
    public void charge(Voltage5V voltage5V) {
        int v = voltage5V.output5V();
        if (v == 5) {
            System.out.println("Charging with 5V...");
        } else {
            System.out.println("Wrong voltage: " + v + "V");
        }
    }
}
