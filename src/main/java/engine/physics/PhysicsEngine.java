package engine.physics;

import engine.event.EventBus;
import engine.ecs.Entity;
import engine.scene.Scene;

import java.awt.geom.Area;
import java.util.ArrayList;

public class PhysicsEngine {
    public static final float DRAG = 0.98F; // closer to 1 means less drag

    public PhysicsEngine(){
        EventBus.subscribe(CollisionEvent.class, event -> onCollision(event.entity(), event.collider()));
    }

    private void onCollision(Entity entity, Entity collider){
        if (entity.getClass() != collider.getClass()){
            System.out.println(collider + " is colliding with " + entity);
        }

    }

    public void update(Scene scene){
        ArrayList<Entity> entities = scene.getEntities();
        checkCollisions(entities);
    }

    private void checkCollisions(ArrayList<Entity> entities){
        int size = entities.size();

        for (int i = 0; i < size; i++){
            Entity e1 = entities.get(i);

            for (int j = i + 1; j < size; j++){
                Entity e2 = entities.get(j);

                if (hasCollision(e1, e2)){
                    EventBus.add(new CollisionEvent(e1, e2));
                }
            }
        }
    }

    private boolean hasCollision(Entity e1, Entity e2) {
        if (!e1.getGeometry().bounds().intersects(e2.getGeometry().bounds())) {
            return false;
        }

        Area testArea = (Area) e1.getGeometry().area().clone();
        testArea.intersect(e2.getGeometry().area());
        return !testArea.isEmpty();
    }
}
