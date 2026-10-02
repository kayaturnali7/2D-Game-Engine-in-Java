package asteroids.entities;

import engine.actors.Actor;
import engine.actors.Entity;
import engine.geometry.Shape;
import engine.physics.Collider;
import engine.scene.Scene;

import java.awt.Color;

public class TestEntity extends Entity implements Collider {

    private static final Color COLOR = Color.WHITE;

    public TestEntity(Scene scene, String name, Shape shape){
        super(scene, name);
    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected Shape shapeInit() {
        return null;
    }

    @Override
    public void onCollision(Actor actor) {

    }
}
