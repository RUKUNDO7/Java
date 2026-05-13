package Behavioral;

import java.util.ArrayList;
import java.util.List;

public class YoutubeChannel implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String video;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for(Observer ob: observers) {
            ob.update(video);
        }
    }

    public void upload(String title) {
        this.video = title;
        notifyObservers();
    }
}