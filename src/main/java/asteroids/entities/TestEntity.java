package asteroids.entities;

import engine.entity.Entity;
import engine.scene.Scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class TestEntity extends Entity {
    public TestEntity(Scene scene){
        super(scene,7, Color.white, true);

        int x = scene.getScreenWidth() / 2;
        int y = scene.getScreenHeight() / 2;
        moveTo(x, y);
        rotate(180);
    }


    @Override
    protected void onUpdate() {

    }

    @Override
    protected void onDraw(Graphics2D g2d) {

    }

    @Override
    protected Shape shapeConfig() {
        double diameter = 100;
        double offset = -diameter/2;

        return new Ellipse2D.Double(offset, offset, diameter, diameter);
    }
}
