package engine.actors;

import engine.geometry.Shape;
import engine.scene.Scene;

public abstract class Actor {

    public final Scene scene;
    public final Shape shape;

    protected double x;
    protected double y;

    protected double direction;

    protected final String name;
    private boolean active = true;

    public Actor(Scene scene, String name){
        this.scene = scene;
        this.x = scene.getCenter().getX();
        this.y = scene.getCenter().getY();
        this.name = name;
        shape = shapeInit();
    }

    public final void update(){
        coreUpdate();
        onUpdate();
        shape.update(x, y, direction);
    }

    public final void collision(Actor actor) {
        onCollision(actor);
    }

    protected abstract void onUpdate();
    protected abstract void coreUpdate();
    protected abstract Shape shapeInit();
    public abstract void onCollision(Actor actor);

    public boolean isActive(){
        return active;
    }

    public void destroy(){
        active = false;
    }

    public String getName(){
        return name;
    }

    public Scene getScene(){return scene;}

    public double getX() {
        return x;
    }

    public double getY() {return y;}

    public double getDirection() {
        return direction;
    }

    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void rotate(double amount) {
        direction -= amount;
    }

    public void rotateTo(double direction) {
        this.direction = direction;
    }
}


