package creational;

public class FactoryMain {
    public  static void main(String[] args) {
        OSFactory factory = new OSFactory();
        OS os = factory.getInstance("closed");
        os.spec();
    }
}