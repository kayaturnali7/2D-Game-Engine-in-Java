package engine.entity;

import engine.data.LinearVelocity;
import engine.util.MathUtils;

public class PhysicsBody {
    private final Entity entity;

    private double velocityX;
    private double velocityY;

    protected PhysicsBody(Entity entity){
        this.entity = entity;
    }

    protected void update(){
        double maxSpeed = entity.getMaxSpeed();
        double velocity  = MathUtils.pythagorean(velocityX, velocityY);

        if (velocity > maxSpeed) {
            velocityX = (velocityX / velocity) * maxSpeed;
            velocityY = (velocityY / velocity) * maxSpeed;
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

    }


    protected LinearVelocity getVelocity(){
        return new LinearVelocity(velocityX, velocityY);
    }

}
