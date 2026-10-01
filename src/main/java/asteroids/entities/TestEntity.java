package asteroids.entities;

import engine.entity.Entity;
import engine.model.Rectangle;
import engine.model.Shape;
import engine.scene.Scene;

import java.awt.Color;
import java.awt.Graphics2D;

public class TestEntity extends Entity {

    private static final Color COLOR = Color.WHITE;

    public TestEntity(Scene scene, String name, Shape shape){
        super(scene,shape, name, 500);
    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected void onDraw(Graphics2D g2d) {

    }

    @Override
    protected Shape shapeInit() {
        return new Rectangle(1,1,COLOR);
    }

    @Override
    protected void onCollision(Entity entity) {

    }


}
