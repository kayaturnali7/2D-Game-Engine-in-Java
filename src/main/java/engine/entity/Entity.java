package engine.entity;

import engine.event.EventBus;
import engine.event.GameEvent;
import engine.model.AABB;
import engine.model.Vector2;
import engine.model.Shape;
import engine.physics.KinematicBody;
import engine.physics.PhysicsBody;
import engine.physics.StaticBody;
import engine.scene.Scene;
import engine.scene.SceneComponent;

import java.awt.Graphics2D;

public abstract class Entity extends SceneComponent implements PhysicsBody {

    public final Vector2 position = new Vector2();
    public double direction;

    public final Vector2 velocity = new Vector2();
    public final Vector2 acceleration = new Vector2();

    private final Shape shape;

    private final int maxSpeed;

    public Entity(Scene scene, String name, int maxSpeed){
        super(scene,name);
        this.scene = scene;
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.shape = shapeInit();

        //this.transform = new Transform(this);
        //this.physics = new PhysicsBody(this);
    }

    public Entity(Scene scene, Shape shape, String name, int maxSpeed){
        super(scene,name);
        this.scene = scene;
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.shape = shape;

        //this.transform = new Transform(this);
        //this.physics = new PhysicsBody(this);
    }

    public final void update(){
        onUpdate();
        onPhysics();
        if (this instanceof KinematicBody){
            ((KinematicBody) this).onInput();
        }

        double x = getPosition().getX();
        double y = getPosition().getY();
        shape.update(x,y,direction);
    }

    public final void draw(Graphics2D g2d){
        onDraw(g2d);

        shape.draw(g2d);
    }

    public void onPhysics(){
        if (this instanceof StaticBody) return;

        velocity.add(acceleration);

        double x = velocity.getX();
        double y = -velocity.getY();

        position.add(x,y);

        if (velocity.magnitude() > maxSpeed) {
            double velocityX = (velocity.getX() / velocity.magnitude()) * maxSpeed;
            double velocityY = (velocity.getY() / velocity.magnitude()) * maxSpeed;
            velocity.set(velocityX, velocityY);
        }

        if (velocity.magnitude() < 0.01) {
            velocity.set(0,0);
        }

        acceleration.set(0,0);
    }

    public final void collision(Entity entity){
        onCollision(entity);
    }

    protected abstract Shape shapeInit();
    protected abstract void onCollision(Entity entity);
    protected abstract void onUpdate();
    protected abstract void onDraw(Graphics2D g2d);

    public void moveTo(double x, double y){
        position.set(x,y);
    }

    public void moveTo(Vector2 vector2){
        position.set(vector2);
    }

    public void setRotation(double angle){
        direction = angle;
    }

    public void rotate(double amount){
        direction -= amount;
    }

    public void setVelocity(int speed, double direction){
        double x = Math.cos(direction) * speed;
        double y = Math.sin(direction) * speed;
        velocity.set(x,y);
    }

    public void applyForce(double force, double direction){
        double x = Math.cos(Math.toRadians(direction)) * force;
        double y = -Math.sin(Math.toRadians(direction)) * force;
        acceleration.set(x,y);
    }

    public Vector2 getVelocity(){
        return velocity;
    }

    public Vector2 getAcceleration(){
        return acceleration;
    }

    public Vector2 getPosition(){
        return position;
    }

    public double getDirection(){
        return direction;
    }

    public int getMaxSpeed(){
        return maxSpeed;
    }

    protected void addEvent(GameEvent event){
        EventBus.add(event);
    }

    public Shape getShape(){
        return shape;
    }

    public Vector2 getVertex(int index){
        return shape.getVertex(index);
    }

    public AABB getBounds(){
        return getShape().getAABB();
    }
}
