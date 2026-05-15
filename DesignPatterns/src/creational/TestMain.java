package creational;

public class TestMain {

    public static void main(String[] args) {
        Browser.getInstance().display();
        EnumSingleton.INSTANCE.display();
    }
}