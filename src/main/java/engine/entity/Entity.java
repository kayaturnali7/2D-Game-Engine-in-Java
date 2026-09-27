package engine.entity;

import engine.data.LinearVelocity;
import engine.event.EventBus;
import engine.event.GameEvent;
import engine.model.Point;
import engine.model.Polygon;
import engine.model.Shape;
import engine.scene.Scene;
import engine.scene.SceneComponent;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Entity implements SceneComponent {

    protected Scene scene;

    private final Transform transform;
    private final Geometry geometry;
    private final PhysicsBody physics;
    //private final Bounds bounds;
    private final Renderer renderer;

    private final int maxSpeed;
    private boolean active = true;

    /** Entity constructor
     */
    public Entity(Scene scene, int maxSpeed, Color color, boolean hasDrag){
        this.scene = scene;
        Shape shape = shapeInit();

        this.maxSpeed = maxSpeed;
        this.transform = new Transform(this);
        this.geometry = new Geometry(this, shape);
        this.physics = new PhysicsBody(this, hasDrag);
        //this.bounds = new Bounds(this);
        this.renderer = new Renderer(this);

    }

    /** The entity's update call.
     */
    public final void update(){
        onUpdate();
        physics.update();
        transform.update();
        geometry.update();
        //bounds.update();
    }

    /** The entity's render call.
     */
    public final void draw(Graphics2D g2d){
        onDraw(g2d);
        renderer.draw(g2d);
    }

    /** The update function specified by the user. It is called before any of the entity's internal components are updated.
     */
    protected abstract void onUpdate();

    /** The draw function specified by the user. It is called before the entity's internal render method.
     */
    protected abstract void onDraw(Graphics2D g2d);

    /** The initial shape configuration method. Must be filled, and return a valid ShapeConfig for the entity to render properly.
     */
    protected abstract Shape shapeInit();

    /** Moves the entity to a specified point (x,y) in the scene.
     * @param x The x position of the target point.
     * @param y The y position of the target point.
     */
    public void moveTo(double x, double y){
        transform.translateTo(x, y);
    }

    /** Sets the entity's move and look rotation to specified angle, in degrees.
     * @param angle The angle to rotate to, in degrees.
     */
    public void setRotation(double angle){
        transform.setRotation(angle);
    }

    /** Rotates the entity by an amount, in degrees.
     * @param amount The amount to rotate by in degrees.
     */
    public void rotate(double amount){
        transform.rotate(amount);
    }

    /** Applies a force to the entity in its current move direction.
     * @param force The amount of force to apply.
     */
    public void applyForce(double force, double direction){
        physics.applyForce(force, direction);
    }

    /** Applies a velocity to a specified angle and speed.
     * @param direction The angle of the velocity, in degrees.
     * @param speed The magnitude of the velocity.
     */
    public void setVelocity(int speed, double direction){
        physics.setVelocity(speed, direction);
    }

    /** Returns the entity's current velocity and its components.
     * @return (velocityX, velocityY, speed)
     */
    public LinearVelocity getVelocity(){
        return physics.getVelocity();
    }

    /** Returns the entity's current directions.
     * @return (lookDirection, moveDirection) in degrees.
     */
    public double getDirection(){return transform.getDirection();}

    public double getX(){
        return transform.getX();
    }

    public double getY(){
        return transform.getY();
    }

    /** Returns the entity's max speed.
     * @return maxSpeed
     */
    public int getMaxSpeed(){
        return maxSpeed;
    }

    /** Destroys the entity. This will remove it from the scene.
     */
    public void destroy(){
        active = false;
    }

    /** Returns if the entity is active.
     * @return If the entity is currently active.
     */
    public boolean isActive(){
        return active;
    }

    protected void addEvent(GameEvent event){
        EventBus.add(event);
    }

    public Shape getShape(){
        return geometry.getShape();
    }

    public Point getVertex(int index){
        Shape shape = getShape();
        if (shape instanceof Polygon){
            return ((Polygon) shape).getVertex(index);
        } else return null;
    }
}
