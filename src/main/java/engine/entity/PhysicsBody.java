package engine.entity;

import engine.data.LinearVelocity;
import engine.util.MathUtils;

public class PhysicsBody {
    private final Entity entity;

    private double velocityX;
    private double velocityY;

    private final boolean hasDrag;

    protected PhysicsBody(Entity entity, boolean hasDrag){
        this.entity = entity;
        this.hasDrag = hasDrag;
    }

    protected void update(){
        double maxSpeed = entity.getMaxSpeed();
        double velocity  = MathUtils.pythagorean(velocityX, velocityY);

        if (velocity > maxSpeed) {
            velocityX = (velocityX / velocity) * maxSpeed;
            velocityY = (velocityY / velocity) * maxSpeed;
        }

        if (hasDrag){
            applyDrag(velocity);
        }
    }

    protected void applyForce(double force, double direction){
        double accelerationX = Math.cos(Math.toRadians(direction)) * force;
        double accelerationY = Math.sin(Math.toRadians(direction)) * force;

        velocityX += accelerationX;
        velocityY += accelerationY;
    }

    protected void setVelocity(int speed, double direction){
        entity.setRotation(direction);

        velocityX = Math.cos(Math.toRadians(direction)) * speed;
        velocityY = Math.sin(Math.toRadians(direction)) * speed;
    }

    protected void applyDrag(double velocity){
        double drag = entity.scene.getDrag();

        velocityX *= drag;
        velocityY *= drag;

        if (velocity < 0.01) {
            velocityX = 0;
            velocityY = 0;
        }
    }

    protected LinearVelocity getVelocity(){
        return new LinearVelocity(velocityX, velocityY);
    }
}
