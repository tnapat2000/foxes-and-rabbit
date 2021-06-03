package io.muic.ooc.fab;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ActorFactory {

    private static final Map<ActorType, Class> actorTypeClassMap = new HashMap<>(){{
        ActorType[] animalTypes = ActorType.values();
        for (int i = 0; i < animalTypes.length; i++){
            put(animalTypes[i], animalTypes[i].getActorClass());
        }
    }};

    public static Actor createActor(ActorType actorType, Field field, Location location){
        Class actorClass = actorTypeClassMap.get(actorType);
        return createActor(actorClass, field, location);
    }

    public static Actor createActor(Class actorClass, Field field, Location loc){
        if (actorClass != null){
            try{
                Actor actor = (Actor) actorClass.getDeclaredConstructor().newInstance();
                actor.initialize(true, field,loc);
                return actor;
            }catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e){
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("UNKNOWN ANIMALTYPE");
    }

}
