package engine.physics;

import engine.entity.Entity;
import engine.model.AABB;
import engine.model.Shape;
import engine.model.Vector2;
import engine.scene.Scene;
import java.util.ArrayList;

public class PhysicsEngine {

    public void update(Scene scene){
        double drag = scene.getDrag();

        ArrayList<Entity> entities = scene.getEntities();
        for (Entity entity : entities){
            if (entity instanceof StaticBody) continue;
            applyDrag(entity, drag);
            entity.onPhysics();
        }

        checkCollisions(entities);
    }

    private void checkCollisions(ArrayList<Entity> entities){
        int size = entities.size();

        for (int i = 0; i < size; i++){
            Entity e2 = entities.get(i);

            for (int j = i + 1; j < size; j++){
                Entity e1 = entities.get(j);
                
                if (AABBCollision(e1, e2)){
                    Shape s1 = e1.getShape();
                    Shape s2 = e2.getShape();

                    if (SATCollision(s1, s2)){
                        e1.collision(e2);
                        e2.collision(e1);
                    }
                }
            }
        }
    }

    private static boolean AABBCollision(Entity e1, Entity e2) {
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

    public static boolean SATCollision(Shape shape1, Shape shape2){

        Shape s1 = shape1;
        Shape s2 = shape2;

        for (int shape = 0; shape < 2; shape++){
            if (shape == 1){
                s1 = shape2;
                s2 = shape1;
            }

            Vector2[] vertices1 = s1.getVertices();
            Vector2[] vertices2 = s2.getVertices();

            for (int a = 0; a < vertices1.length; a++){
                int b = (a + 1) % vertices1.length;
                Vector2 axisProj = new Vector2(
                        vertices1[a].getY() - vertices1[b].getY(),
                        vertices1[b].getX() - vertices1[a].getX()
                );

                double min_r1 = Double.POSITIVE_INFINITY, max_r1 = Double.NEGATIVE_INFINITY;
                for (Vector2 vertex : vertices1) {
                    double q = vertex.dot(axisProj);
                    min_r1 = Math.min(min_r1, q);
                    max_r1 = Math.max(max_r1, q);
                }

                double min_r2 = Double.POSITIVE_INFINITY, max_r2 = Double.NEGATIVE_INFINITY;
                for (Vector2 vector2 : vertices2) {
                    double q = vector2.dot(axisProj);
                    min_r2 = Math.min(min_r2, q);
                    max_r2 = Math.max(max_r2, q);
                }

                if (!(max_r2 >= min_r1 && max_r1 >= min_r2)){
                    return  false;
                }
            }
        }
        return true;
    }

    private void applyDrag(Entity entity, double drag){
        entity.velocity.scale(drag);
    }
}
