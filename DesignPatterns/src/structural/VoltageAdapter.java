package structural;

public class VoltageAdapter implements Voltage5V {
    private final Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        int source = voltage220V.output220V();
        return source / 44;
    }
}
