package engine.ecs;

import engine.data.Direction;
import engine.data.Position;
import engine.util.MathUtils;

public class Transform {
    private final Entity currentEntity;

    protected Transform(Entity entity){
        currentEntity = entity;
    }

    private float x = 400;
    private float y = 400;

    private double lookDirection = 90;
    private double moveDirection = lookDirection;

    protected void update(){
        translate();
        wrapRotations();
    }

    protected void translate(){
        x += currentEntity.getVelocity().x();
        y -= currentEntity.getVelocity().y();
    }

    protected void translateTo(float x, float y){
        this.x = x;
        this.y = y;
    }

    protected void setRotation(double angle){
        lookDirection = angle;
        updateMoveDirection();
    }

    protected void rotate(double amount){
        lookDirection -= amount;
        updateMoveDirection();
    }

    protected Position getPosition(){
        return new Position(x,y);
    }

    protected Direction getDirection(){
        return new Direction(lookDirection, moveDirection);
    }

    protected void updateMoveDirection(){
        moveDirection = lookDirection;
    }

    private void wrapRotations(){
        lookDirection = MathUtils.wrapAngle(lookDirection);
        moveDirection = MathUtils.wrapAngle(moveDirection);
    }
}
