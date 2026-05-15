package creational;

public class OSFactory {
    public OS getInstance (String message) {
        if(message.equals("closed")) {
            return new IOS();
        } else {
            return new Android();
        }

    }
}