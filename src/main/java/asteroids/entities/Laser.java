package asteroids.entities;

import engine.actors.Actor;
import engine.geometry.Circle;
import engine.scene.Scene;
import engine.geometry.Shape;

import java.awt.Color;

public class Laser extends Actor{
    private static final int SIZE = 2;
    private static final double LIFE_TIME = 0.6;
    private static final Color COLOR = Color.WHITE;

    private int lifeTick = 0;

    public Laser(Scene scene, double x, double y, double direction, int index){
        super(scene, "laser");
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
    protected Shape shapeInit() {
        return new Circle(SIZE, 16, COLOR);
    }


    @Override
    public void onCollision(Actor actor) {

    }
}
