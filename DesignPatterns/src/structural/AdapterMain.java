package structural;

public class AdapterMain {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Voltage220V wallSocket = new Voltage220V();
        Voltage5V adapter = new VoltageAdapter(wallSocket);
        phone.charge(adapter);
    }
}
