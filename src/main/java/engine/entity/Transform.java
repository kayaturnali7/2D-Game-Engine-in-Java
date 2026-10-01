package engine.entity;

public class Transform{

    private final Entity entity;

    private double x;
    private double y;

    private double direction;

    protected Transform(Entity entity){
        this.entity = entity;

        this.x = entity.getScene().getCenter().getX();
        this.y = entity.getScene().getCenter().getY();

        this.direction = 90;
    }

    protected void update(){
        translate();

        if (Math.abs(direction) >= 360){
            direction = 0;
        }
    }

    protected void translate(){
        x += entity.getVelocity().getX();
        y -= entity.getVelocity().getY();
    }

    protected void translateTo(double x, double y){
        this.x = x;
        this.y = y;
    }

    protected void setRotation(double angle){
        direction = angle;
    }

    protected void rotate(double amount){
        direction -= amount;
    }

    protected double getX() {
        return x;
    }

    protected double getY() {
        return y;
    }

    protected double getDirection(){
        return direction;
    }

}
