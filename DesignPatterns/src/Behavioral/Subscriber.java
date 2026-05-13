package Behavioral;

public class Subscriber implements Observer{

    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public void update(String message) {
        System.out.println(name+ " received notification, new video uploaded-->" +message);
    }

}