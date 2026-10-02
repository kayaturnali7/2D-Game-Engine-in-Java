package engine.actors;

import engine.data.Vector2;
import engine.geometry.Shape;
import engine.physics.AABB;
import engine.physics.PhysicsBody;
import engine.render.RenderComponent;
import engine.scene.Scene;

public abstract class Entity extends Actor implements RenderComponent, PhysicsBody {

    public final Vector2 position = new Vector2();
    public final Vector2 velocity = new Vector2();
    private double direction;

    private final Vector2 acceleration = new Vector2();

    protected Shape shape;

    public Entity(Scene scene, String name){
        super(scene, name);
        shape = shapeInit();
    }

    public Entity(Scene scene, String name, Shape shape){
        super(scene, name);
        this.shape = shape;
    }

    protected abstract Shape shapeInit();

    @Override
    public void update(){
        onUpdate();
        physicsStep(position, velocity);
        shape.update(position.getX(), position.getY(), direction);
    }

    @Override
    public Shape getShape(){
        return shape;
    }

    public AABB getAABB(){
        return getShape().getAABB();
    }

    public Vector2 getPosition(){
        return position;
    }

    public double getDirection(){
        return direction;
    }

    public void applyForce(double force, double direction){
        double x = Math.cos(Math.toRadians(direction)) * force;
        double y = -Math.sin(Math.toRadians(direction)) * force;

        velocity.add(x,y);
    }

    public Vector2 getAcceleration(){
        return acceleration;
    }

    public void moveTo(Vector2 vector2){
        position.set(vector2);
    }

    public void moveTo(double x, double y){
        position.set(x,y);
    }

    public void setVelocity(double x, double y){
        velocity.set(x,y);
    }

    public void rotateTo(double direction){
        this.direction = direction;
    }

    public void rotate(double amount){
        this.direction += amount;
    }


}
