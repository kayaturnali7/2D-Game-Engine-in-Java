package engine.physics;

import engine.event.EventBus;
import engine.entity.Entity;
import engine.scene.Scene;

import java.awt.geom.Area;
import java.util.ArrayList;

public class PhysicsEngine {

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
