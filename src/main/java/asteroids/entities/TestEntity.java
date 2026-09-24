package asteroids.entities;

import engine.entity.Entity;
import engine.scene.Scene;
import engine.util.MathUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class TestEntity extends Entity {

    public TestEntity(Scene scene){
        super(scene,7, Color.white, true);
    }


    @Override
    protected void onUpdate() {
    }

    @Override
    protected void onDraw(Graphics2D g2d) {

    }

    @Override
    protected Shape shapeInit() {
        double diameter = 100;
        double offset = -diameter/2;

        return new Ellipse2D.Double(offset, offset, diameter, diameter);
    }
}
