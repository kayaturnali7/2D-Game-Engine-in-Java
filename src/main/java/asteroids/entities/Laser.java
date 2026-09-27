package asteroids.entities;

import engine.entity.Entity;
import engine.model.Circle;
import engine.scene.Scene;
import engine.model.Shape;

import java.awt.Color;
import java.awt.Graphics2D;

public class Laser extends Entity {
    private static final int SIZE = 3;
    private static final double LIFE_TIME = 0.6;

    private float lifeTick = 0;

    public Laser(Scene scene, double x, double y, double direction ){
        super(scene, 20, Color.white, false);
        moveTo(x,y);
        setRotation(direction);
        setVelocity(getMaxSpeed(), direction);
    }

    @Override
    protected void onUpdate() {
        double lifeTimeFrames = LIFE_TIME * scene.getFps();
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
    protected Shape shapeInit() {
        return new Circle(SIZE);
    }
}
