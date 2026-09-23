package engine.entity;

import engine.data.Velocity;
import engine.util.MathUtils;

public class PhysicsBody {
    private final Entity currentEntity;

    private float velocityX;
    private float velocityY;
    private float speed;

    private final boolean hasDrag;

    protected PhysicsBody(Entity entity, boolean hasDrag){
        currentEntity = entity;
        this.hasDrag = hasDrag;
    }

    protected void update(){
        speed = MathUtils.pythagorean(velocityX, velocityY);

        if (hasDrag){
            applyDrag();
        }
    }

    protected void applyForce(float force){
        double moveDirection = currentEntity.getDirection().move();
        float maxSpeed = currentEntity.getMaxSpeed();

        float forceX = (float) Math.cos(Math.toRadians(moveDirection)) * force;
        float forceY = (float) Math.sin(Math.toRadians(moveDirection)) * force;

        velocityX += forceX;
        velocityY += forceY;

        float velocity = getVelocity().speed();

        if (velocity > maxSpeed) {
            velocityX = (velocityX / velocity) * maxSpeed;
            velocityY = (velocityY / velocity) * maxSpeed;
        }
    }

    protected void setVelocity(int speed, double direction){
        currentEntity.setRotation(direction);

        velocityX = (float) Math.cos(Math.toRadians(direction)) * speed;
        velocityY = (float) Math.sin(Math.toRadians(direction)) * speed;
    }

    protected void applyDrag(){
        float drag = currentEntity.scene.getDrag();

        velocityX *= drag;
        velocityY *= drag;

        float velocity = getVelocity().speed();

        if (velocity < 0.01f) {
            velocityX = 0;
            velocityY = 0;
        }
    }

    protected Velocity getVelocity(){
        return new Velocity(velocityX, velocityY, speed);
    }
}
