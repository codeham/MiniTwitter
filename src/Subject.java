import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void register(Observer newObserver) {
        observers.add(newObserver);
    }

    public void notifyObserver() {
        for(Observer observer: observers){
            observer.updateFeed(this);
        }
    }
}
