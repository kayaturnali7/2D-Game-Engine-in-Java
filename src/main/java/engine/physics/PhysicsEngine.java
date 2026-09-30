package engine.physics;

import engine.entity.Entity;
import engine.model.AABB;
import engine.scene.Scene;

import java.util.ArrayList;

public class PhysicsEngine {

    public void update(Scene scene){
        ArrayList<Entity> entities = scene.getEntities();
        checkCollisions(entities);
    }

    private void checkCollisions(ArrayList<Entity> entities){
        int size = entities.size();

        for (int i = 0; i < size; i++){
            Entity e2 = entities.get(i);

            for (int j = i + 1; j < size; j++){
                Entity e1 = entities.get(j);

                if (AABBCollision(e1, e2)){
                    System.out.println(e1 + " colliding with " + e2);
                }
            }
        }
    }

    private boolean AABBCollision(Entity e1, Entity e2) {
        AABB e1Bounds = e1.getBounds();
        AABB e2Bounds = e2.getBounds();

        int e1X = e1Bounds.getX();
        int e1Y = e1Bounds.getY();
        int e1Width = e1Bounds.getWidth();
        int e1Height = e1Bounds.getHeight();

        int e2X = e2Bounds.getX();
        int e2Y = e2Bounds.getY();
        int e2Width = e2Bounds.getWidth();
        int e2Height = e2Bounds.getHeight();

        return e1X < e2X + e2Width && e1X + e1Width > e2X && e1Y < e2Y + e2Height && e1Y + e1Height > e2Y;
    }
}
