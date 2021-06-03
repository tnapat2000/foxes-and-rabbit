package io.muic.ooc.fab.observer;

import io.muic.ooc.fab.Field;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {


    private List<Observer> observers = new ArrayList<>();

    public final void addObserver(Observer observer){
        observers.add(observer);
    }

    public final void notifyAllObservers(int step, Field field){
        for (Observer obs : observers){
            obs.update(step, field);
        }
    }

}
