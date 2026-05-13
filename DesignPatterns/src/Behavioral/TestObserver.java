package Behavioral;

public class TestObserver {
    public static void main(String[] args){
        YoutubeChannel channel1 = new YoutubeChannel();
        Subscriber sub1 = new Subscriber("Larissa");
        Subscriber sub2 = new Subscriber("Florence");
        channel1.addObserver(sub1);
        channel1.addObserver(sub2);
        channel1.upload("Design Patterns in Java");
    }
}

