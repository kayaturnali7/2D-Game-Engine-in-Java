package game.entities;

import game.data.ShapeConfig;
import game.entitycomponents.Entity;

import java.awt.Color;
import java.awt.Graphics2D;

public class TestEntity extends Entity {
    public TestEntity(){
        super(7, Color.white, true);
        moveTo(400,400);
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
        int [] xPoints = new int[] {163, -218, -218, -87, -79, -71, -27, 11, 11, 14, 135, 171};
        int [] yPoints = new int[] {-238, -241, -103, -82, 58, 134, 186, 110, 56, -81, -86, -164};
        return new ShapeConfig(xPoints, yPoints, false, false);
    }
}
