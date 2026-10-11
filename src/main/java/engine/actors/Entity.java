package engine.actors;

import engine.data.Vector2;
import engine.scene.Scene;

public abstract class Entity extends Actor{

    private double vx, vy;
    private double ax, ay;

    public Entity(Scene scene, String name){
        super(scene, name);
    }

    @Override
    public void coreUpdate(){
        vx += ax;
        vy += ay;

        x += vx;
        y += vy;

        ax = 0;
        ay = 0;
    }

    public void applyForce(double direction, double magnitude){
        double x = Math.cos(Math.toRadians(direction)) * magnitude;
        double y = Math.sin(Math.toRadians(direction)) * magnitude;

        ax += x;
        ay += y;
    }

    public void setVelocity(Vector2 velocity){
        velocity.set(velocity);
    }

    public void setVelocity(double direction, double magnitude) {
        double x = Math.cos(Math.toRadians(direction)) * magnitude;
        double y = Math.sin(Math.toRadians(direction)) * magnitude;

        vx = x;
        vy = y;
    }
}
