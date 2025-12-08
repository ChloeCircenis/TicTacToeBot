package TicTacToe;

import java.util.*;

public class MoveSubject {
    public enum EventType {
        GameReset,
        Click,
        GameStart,
        All,
    }
    private final Map<EventType, Set<MoveObserver>> observers = new HashMap<>();

    public void addObserver(MoveObserver observer, EventType eventType) {
        if(eventType == EventType.All) {
            addObserver(observer,  EventType.Click);
            addObserver(observer,  EventType.GameReset);
            addObserver(observer,  EventType.GameStart);
            return;
        }
        Set<MoveObserver> list = observers.computeIfAbsent(eventType, k -> new HashSet<>());
        list.add(observer);
    }
    public void removeObserver(MoveObserver observer, EventType eventType) {
        Set<MoveObserver> list = observers.computeIfAbsent(eventType, k -> new HashSet<>());
        if (list != null) {
            list.remove(observer);
        }
    }

    public void notifyMove(int x, int y){
        Set<MoveObserver> list = observers.computeIfAbsent(EventType.Click, k -> new HashSet<>());
        for (MoveObserver observer : list) {
            observer.notifyMove(x, y);
        }
    }

    public void notifyReset() {
        System.out.println("Resetting!");
        Set<MoveObserver> list = observers.computeIfAbsent(EventType.GameReset, k -> new HashSet<>());
        for (MoveObserver observer : list) {
            observer.notifyReset();
        }
    }

    public void notifyStart() {
        System.out.println("Starting!");
        Set<MoveObserver> list = observers.computeIfAbsent(EventType.GameStart, k -> new HashSet<>());
        for (MoveObserver observer : list) {
            observer.notifyStart();
        }
    }
}
