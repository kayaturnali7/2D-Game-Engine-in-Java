package asteroids.entities;

import engine.data.ShapeConfig;
import engine.entity.Entity;

import java.awt.Color;
import java.awt.Graphics2D;

public class TestEntity extends Entity {
    public TestEntity(){
        super(7, Color.white, true);
        moveTo(200,200);
        rotate(180);
    }


    @Override
    protected void onUpdate() {

    }

    @Override
    protected void onDraw(Graphics2D g2d) {
    }

    @Override
    protected ShapeConfig shapeConfig() {
        int [] xPoints = new int[] {0, 100, -100};
        int [] yPoints = new int[] {100, -100, -100};
        return new ShapeConfig(xPoints, yPoints, false, false);
    }
}
