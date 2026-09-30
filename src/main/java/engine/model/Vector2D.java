package engine.model;

import engine.util.MathUtils;

public class Vector2D {

    private double x;
    private double y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2D(){
        this.x = 0;
        this.y = 0;
    }

    public void setLocation(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getMagnitude(){
        return Math.sqrt((x*x) + (y*y) );
    }

    public Vector2D normalize(){
        double mag = getMagnitude();
        if (mag == 0){
            return new Vector2D();
        }
        return new Vector2D(x/mag, y/mag);
    }

    public double distanceTo(Vector2D point){
        return MathUtils.distance2D(this, point);
    }

    public double getAngle(){
        return Math.toDegrees(Math.atan2(y,x));
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }

}
