package game.engine;

import game.entities.Laser;
import game.entities.Player;
import game.entities.TestEntity;
import game.entitycomponents.Entity;
import game.events.LaserFiredEvent;
import java.util.ArrayList;

public class GameWorld {

    private static final ArrayList<Entity> entities = new ArrayList<>();
    private static final ArrayList<Entity> pending = new ArrayList<>();

    public GameWorld(){
        entities.add(new Player());
        entities.add(new TestEntity());

        EventBus.subscribe(LaserFiredEvent.class, event -> onLaserFired(event.x(), event.y(), event.angle()));
    }

    public void update(){
        updateEntities();
    }


    private void updateEntities(){
        // update each entity's logic
        for (Entity entity : entities){
            entity.update();
        }

        // clear dead entities
        entities.removeIf(entity -> !entity.isAlive());

        // add any pending entities
        if (!pending.isEmpty()){
            entities.addAll(pending);
            pending.clear();
        }
    }


    private void spawnEntity(Entity entity){
        pending.add(entity);
    }

    private void onLaserFired(float x, float y, double direction){
        spawnEntity(new Laser(x, y, direction));
    }

    public static ArrayList<Entity> getEntities(){
        return entities;
    }
}
