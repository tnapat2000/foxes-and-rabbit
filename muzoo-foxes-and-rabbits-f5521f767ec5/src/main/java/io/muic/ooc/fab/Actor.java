package io.muic.ooc.fab;

import java.util.List;

public abstract class Actor {

    // Whether the animal is alive or not.
    private boolean alive = true;
    protected Location location;
    // The field occupied.
    protected Field field;

    public void initialize(boolean randomAge, Field field, Location location){
        this.field = field;
        setLocation(location);
    }

    public Location getLocation() {
        return location;
    }
    /**
     * Place the rabbit at the new location in the given field.
     *
     * @param newLocation The rabbit's new location.
     */
    protected void setLocation(Location newLocation) {
        if (location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    protected abstract void act(List<Actor> actors);

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Indicate that the fox is no longer alive. It is removed from the field.
     */

    protected abstract Location moveToNewLocation();

}

