package asteroids.entities;

import engine.entity.Entity;
import engine.scene.Scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

public class Laser extends Entity {
    private static final int SIZE = 1;
    private static final float LIFE_TIME = 0.6F;

    private float lifeTick = 0;

    public Laser(Scene scene, float x, float y, double direction ){
        super(scene, 20, Color.white, false);
        moveTo(x,y);
        setRotation(direction);
        setVelocity(getMaxSpeed(), direction);
    }

    @Override
    protected void onUpdate() {
        float lifeTimeFrames = LIFE_TIME * scene.getFps();
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
    protected Shape shapeConfig() {
        return new Rectangle(SIZE, SIZE);
    }
}
