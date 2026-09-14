package engine.entity;

import engine.data.Direction;
import engine.data.Position;
import engine.data.ShapeConfig;
import engine.data.Velocity;

import java.awt.Color;
import java.awt.Graphics2D;
public abstract class Entity {
    private final Transform transform;
    private final Geometry geometry;
    private final PhysicsBody physics;
    private final Bounds bounds;
    private final Renderer renderer;

    private final int maxSpeed;
    private boolean alive = true;

    /// Entity constructor
    public Entity(int maxSpeed, Color color, boolean hasDrag){
        this.maxSpeed = maxSpeed;
        this.transform = new Transform(this);
        this.geometry = new Geometry(this);
        this.physics = new PhysicsBody(this, hasDrag);
        this.bounds = new Bounds(this);
        this.renderer = new Renderer(this, color);
        createShape();
    }


    /// The entity's update call.
    public final void update(){
        onUpdate();

        physics.update();
        transform.update();
        geometry.update();
        bounds.update();
    }

    /// The entity's render call.
    public final void draw(Graphics2D g2d){
        onDraw(g2d);
        renderer.draw(g2d);
    }

    private void createShape(){
        ShapeConfig config = shapeConfig();

        geometry.createShape(config.xPoints(), config.yPoints());
        renderer.setFill(config.fillShape());
        renderer.setDrawBoundaryBox(config.drawBounds());
    }

    /// The update function specified by the user. It is called before any of the entity's internal components are updated.
    protected abstract void onUpdate();

    /// The draw function specified by the user. It is called before the entity's internal render method.
    protected abstract void onDraw(Graphics2D g2d);

    /// The initial shape configuration method. Must be filled, and return a valid ShapeConfig for the entity to work properly.
    protected abstract ShapeConfig shapeConfig();

    /// Moves the entity to a specified point (x,y) in the scene.
    /// @param X The x position of the target point.
    /// @param Y The y position of the target point.
    protected void moveTo(float X, float Y){
        transform.translateTo(X, Y);
    }

    /// Sets the entity's move and look rotation to specified angle, in degrees.
    /// @param angle The angle to rotate to, in degrees.
    protected void setRotation(double angle){
        transform.setRotation(angle);
    }

    /// Rotates the entity by an amount, in degrees.
    /// @param amount The amount to rotate by in degrees.
    protected void rotate(double amount){
        transform.rotate(amount);
    }

    /// Applies a force to the entity in its current move direction.
    /// @param force The amount of force to apply.
    protected void applyForce(float force){
        physics.applyForce(force);
    }

    /// Applies a velocity to a specified angle, in degrees, and speed.
    /// @param direction The angle of the velocity, in degrees.
    /// @param speed The magnitude of the speed.
    protected void setVelocity(int speed, double direction){
        physics.setVelocity(speed, direction);
    }

    /// Returns the entity's current velocity and its components: (velocityX, velocityY, speed).
    public Velocity getVelocity(){
        return physics.getVelocity();
    }

    /// Returns the entity's current geometry and its components: (activeShape, activeArea, bounds).
    public engine.data.Geometry getGeometry(){
        return geometry.getGeometry();
    }

    /// Returns the entity's current direction and its components: (lookDirection, moveDirection).
    public Direction getDirection(){return transform.getDirection();}

    /// Returns the entity's current position in the scene: (x,y)
    public Position getPosition(){return transform.getPosition();}

    /// Returns the entity's max speed.
    public int getMaxSpeed(){
        return maxSpeed;
    }

    /// Kills the entity. This will remove it from the scenes entity list.
    public void kill(){
        alive = false;
    }

    /// Returns if the entity is alive.
    public boolean isAlive(){
        return alive;
    }
}
