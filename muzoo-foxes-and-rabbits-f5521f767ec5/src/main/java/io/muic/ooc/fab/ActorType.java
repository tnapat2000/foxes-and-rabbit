package io.muic.ooc.fab;

import java.awt.Color;

public enum ActorType {
    RABBIT(0.12, Rabbit.class,Color.YELLOW ),
    FOX(0.08, Fox.class, Color.BLUE),
    TIGER(0.04, Tiger.class, Color.RED),
    HUNTER(0.005, Hunter.class, Color.GREEN);

    private final double creationProbability;

    private final Class actorClass;

    private final Color color;

    ActorType(double creationProbability, Class actorClass, Color color){
        this.creationProbability = creationProbability;
        this.actorClass = actorClass;
        this.color = color;
    }

    public Class getActorClass() {
        return actorClass;
    }

    double getCreationProbability(){
        return creationProbability;
    }

    public Color getColor() {
        return color;
    }

}