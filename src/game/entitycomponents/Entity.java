package game.entitycomponents;

import game.data.Direction;
import game.data.Position;
import game.data.ShapeConfig;
import game.data.Velocity;

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

    public Entity(int maxSpeed, Color color, boolean hasDrag){
        this.maxSpeed = maxSpeed;
        this.transform = new Transform(this);
        this.geometry = new Geometry(this);
        this.physics = new PhysicsBody(this, hasDrag);
        this.bounds = new Bounds(this);
        this.renderer = new Renderer(this, color);
        createShape();
    }

    public final void update(){
        onUpdate();

        physics.update();
        transform.update();
        geometry.update();
        bounds.update();
    }

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

    protected abstract void onUpdate();
    protected abstract void onDraw(Graphics2D g2d);
    protected abstract ShapeConfig shapeConfig();

    protected void moveTo(float X, float Y){
        transform.translateTo(X, Y);
    }

    protected void setRotation(double angle){
        transform.setRotation(angle);
    }

    protected void rotate(double amount){
        transform.rotate(amount);
    }

    protected void applyForce(float force){
        physics.applyForce(force);
    }

    protected void applyVelocity(int speed, double direction){
        physics.setVelocity(speed, direction);
    }

    public Velocity getVelocity(){
        return physics.getVelocity();
    }

    public game.data.Geometry getGeometry(){
        return geometry.getGeometry();
    }

    public Direction getDirection(){return transform.getDirection();}

    public Position getPosition(){return transform.getPosition();}

    public int getMaxSpeed(){
        return maxSpeed;
    }

    public void kill(){
        alive = false;
    }

    public boolean isAlive(){
        return alive;
    }
}
