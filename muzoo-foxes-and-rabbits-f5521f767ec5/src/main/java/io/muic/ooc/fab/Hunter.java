package io.muic.ooc.fab;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Hunter extends Actor {

    // Random generator

    public void initialize(boolean randomAge, Field field, Location location){
        super.initialize(randomAge, field, location);
    }

    private Location huntAnimal(){
        List<Location> adjacent = field.adjacentLocations(location);
        Iterator<Location> it = adjacent.iterator();
        while (it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if (animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if (rabbit.isAlive()) {
                    rabbit.setDead();
                    return where;
                }
            }
            else if (animal instanceof Fox) {
                Fox fox = (Fox) animal;
                if (fox.isAlive()) {
                    fox.setDead();
                    return where;
                }
            }
            else if (animal instanceof Tiger) {
                Tiger tiger = (Tiger) animal;
                if (tiger.isAlive()) {
                    tiger.setDead();
                    return where;
                }
            }
        }
        return null;
    }

    @Override
    public void act(List<Actor> actors) {
        // Move towards a source of food if found.
        Location newLocation = moveToNewLocation();
        // See if it was possible to move.
        if (newLocation != null) {
            setLocation(newLocation);
        }
    }


    @Override
    protected Location moveToNewLocation(){
        Location newLocation = huntAnimal();
        if (newLocation == null) {
            // No food found - try to move to a free location.
            newLocation = field.freeAdjacentLocation(getLocation());
        }
        return newLocation;
    }

}
