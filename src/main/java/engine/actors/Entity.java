package engine.actors;

import engine.scene.Scene;

public abstract class Entity extends Actor {

    public double vx, vy;
    public double ax, ay;

    public Entity(Scene scene, String name){
        super(scene, name);
    }

    @Override
    public void coreUpdate() {
        vx += ax;
        vy += ay;

        x += vx;
        y += vy;

        ax = 0;
        ay = 0;
    }

    public void applyForce(double direction, double magnitude) {
        double x = Math.cos(Math.toRadians(direction)) * magnitude;
        double y = Math.sin(Math.toRadians(direction)) * magnitude;

        ax += x;
        ay += y;
    }

    public void setVelocityComponents(double vx, double vy) {
        this.vx = vy;
        this.vy = vy;
    }

    public void multiplyVelocityComponents(double scale){
        this.vx *= scale;
        this.vy *= scale;
    }

    public void setVelocityVector(double direction, double magnitude) {
        double x = Math.cos(Math.toRadians(direction)) * magnitude;
        double y = Math.sin(Math.toRadians(direction)) * magnitude;

        vx = x;
        vy = y;
    }
}
