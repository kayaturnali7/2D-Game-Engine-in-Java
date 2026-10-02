package engine.physics;

import engine.actors.Actor;
import engine.geometry.Shape;

public interface Collider {

    default void collision(Actor actor){
        onCollision(actor);
    }

    void onCollision(Actor actor);
    Shape getShape();
}
