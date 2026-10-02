package engine.physics;

import engine.data.Vector2;

public interface PhysicsBody {
    default void physicsStep(Vector2 position, Vector2 velocity){
        position.add(velocity);
    }
}
