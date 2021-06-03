package io.muic.ooc.fab;

import java.util.List;
import java.util.Random;

public abstract class Animal extends Actor{

    private static final Random RANDOM = new Random();

    // Individual characteristics (instance fields).
    protected int age = 0;


    public void initialize(boolean randomAge, Field field, Location location){
        super.initialize(randomAge,field,location);
        if (randomAge) {
            age = RANDOM.nextInt(getMaxAge());
        }
    }
    /**
     * Check whether the animal is alive or not.
     *
     * @return true if the animal is still alive.
     */
    public abstract int getMaxAge();

    /**
     * Increase the age. This could result in the rabbit's death.
     */
    protected void incrementAge() {
        age++;
        if (age > getMaxAge()) {
            setDead();
        }
    }

    protected void setDead() {
        setAlive(false);
        if (location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }
    /**
     * Generate a number representing the number of births, if it can breed.
     *
     * @return The number of births (may be zero).
     */
    protected int breed() {
        int births = 0;
        if (canBreed() && RANDOM.nextDouble() <= getBreedingProbability()) {
            births = RANDOM.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }

    protected abstract double getBreedingProbability();
    protected abstract int getMaxLitterSize();

    /**
     * A rabbit can breed if it has reached the breeding age.
     *
     * @return true if the rabbit can breed, false otherwise.
     */
    private boolean canBreed() {
        return age >= getBreedingAge();
    }

    protected abstract int getBreedingAge();

    protected Actor createYoung(Field field, Location location){
        return ActorFactory.createActor(this.getClass(), field,location);
    }


    /**
     * Check whether or not this rabbit is to give birth at this step. New
     * births will be made into free adjacent locations.
     *
     * @param newAnimals A list to return newly born rabbits.
     */
    protected void giveBirth(List<Actor> newAnimals) {
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
        List<Location> free = field.getFreeAdjacentLocations(location);
        int births = breed();
        for (int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Actor young = createYoung( field, loc);
            newAnimals.add(young);
        }
    }

    public abstract int getFoodValue();

    @Override
    public void act(List<Actor> animals){
        incrementAge();
        if (isAlive()) {
            giveBirth(animals);
            // Move towards a source of food if found.
            Location newLocation = moveToNewLocation();

            // See if it was possible to move.
            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                // Overcrowding.
                setDead();
            }
        }
    }



}
