package engine.entity;

import engine.data.Direction;
import engine.data.Position;
import engine.data.Velocity;
import engine.event.EventBus;
import engine.event.GameEvent;
import engine.scene.Scene;
import engine.scene.SceneInterface;
import engine.util.GeneralUtil;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;

public abstract class Entity implements SceneInterface {

    protected Scene scene;

    private final Transform transform;
    private final Geometry geometry;
    private final PhysicsBody physics;
    private final Bounds bounds;
    private final Renderer renderer;

    private final int maxSpeed;
    private boolean active = true;

    /** Entity constructor
     */
    public Entity(Scene scene, int maxSpeed, Color color, boolean hasDrag){
        this.scene = scene;
        this.maxSpeed = maxSpeed;
        this.transform = new Transform(this);
        this.geometry = new Geometry(this);
        this.physics = new PhysicsBody(this, hasDrag);
        this.bounds = new Bounds(this);
        this.renderer = new Renderer(this, color);
        createShape();
    }

    /** The entity's update call.
     */
    public final void update(){
        onUpdate();
        physics.update();
        transform.update();
        geometry.update();
        bounds.update();
    }

    /** The entity's render call.
     */
    public final void draw(Graphics2D g2d){
        onDraw(g2d);
        renderer.draw(g2d);
    }

    private void createShape(){
        Shape shape = shapeInit();

        geometry.createShape(shape);
        renderer.setFill(false);
        renderer.setDrawBoundaryBox(false);
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
    public void moveTo(float x, float y){
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
    public void applyForce(float force){
        physics.applyForce(force);
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
    public Velocity getVelocity(){
        return physics.getVelocity();
    }

    /**
     * Returns the entity's current geometry.
     *
     * @return (activeShape, activeArea, bounds)
     */
    public engine.data.Geometry getGeometry(){
        return geometry.getGeometry();
    }

    /** Returns the entity's current directions.
     * @return (lookDirection, moveDirection) in degrees.
     */
    public Direction getDirection(){return transform.getDirection();}

    /** Returns the entity's current position in the scene.
     * @return (x,y)
     */
    public Position getPosition(){return transform.getPosition();}

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

    /** Returns the specified vertex located in initial x and y coordinate list.
     * @param shape The swing shape to find the vertex in.
     * @param targetIndex The index of the vertex you want to get the coordinate of.
     * @return Returns a point that holds an x and y coordinate.
     */
    protected Point2D.Float getVertex(Shape shape, int targetIndex){
        return GeneralUtil.getVertex(shape, targetIndex);
    }
}
