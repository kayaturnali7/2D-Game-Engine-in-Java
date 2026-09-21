package asteroids.entities;

import engine.data.ShapeConfig;
import engine.core.Loop;
import engine.entity.Entity;

import java.awt.Color;
import java.awt.Graphics2D;

public class Laser extends Entity {
    private static final int SIZE = 3;
    private static final float LIFE_TIME = 0.6F;

    private float lifeTick = 0;

    public Laser(float x, float y, double direction ){
        super(20, Color.white, false);
        moveTo(x,y);
        setRotation(direction);
        setVelocity(getMaxSpeed(), direction);
    }

    @Override
    protected void onUpdate() {
        float lifeTimeFrames = LIFE_TIME * Loop.FPS;
        if (lifeTick >= lifeTimeFrames){
            destroy();
        } else{
            lifeTick += 1;
        }
    }

    @Override
    protected void onDraw(Graphics2D g2d) {

    }

    @Override
    protected ShapeConfig shapeConfig() {
        double length = SIZE;

        //TOP LEFT
        double v1x = -length /2;
        double v1y = length /2;

        //TOP RIGHT
        double v2x = length /2;
        double v2y = length /2;

        //BOTTOM RIGHT
        double v3x = length /2;
        double v3y = -length /2;

        //BOTTOM LEFT
        double v4x = -length /2;
        double v4y = -length /2;


        int[] xPoints = new int[]{(int) v1x, (int) v2x, (int) v3x, (int) v4x};
        int[] yPoints = new int[]{(int) v1y, (int) v2y, (int) v3y, (int) v4y};

        return new ShapeConfig(xPoints, yPoints, false, true);
    }
}
