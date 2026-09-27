package asteroids.entities;

import engine.entity.Entity;
import engine.model.Circle;
import engine.model.Shape;
import engine.scene.Scene;

import java.awt.Graphics2D;

public class TestEntity extends Entity {

    public TestEntity(Scene scene){
        super(scene,7, true);
    }

    double step = 0;

    @Override
    protected void onUpdate() {
    }

    @Override
    protected void onDraw(Graphics2D g2d) {

    }

    @Override
    protected Shape shapeInit() {
        return new Circle(50);
    }
}
