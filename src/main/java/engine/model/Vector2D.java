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

    public void add(Vector2D v){
        this.x += v.x;
        this.y += v.y;
    }

    public void sub(Vector2D v){
        this.x -= v.x;
        this.y -= v.y;
    }

    public void scale(int amount){
        this.x *= amount;
        this.y *= amount;
    }

    public double magnitude(){
        return Math.sqrt((x*x) + (y*y) );
    }

    public Vector2D normalize(){
        double mag = magnitude();
        if (mag == 0){
            return new Vector2D();
        }
        return new Vector2D(x/mag, y/mag);
    }

    public double distance(Vector2D point){
        return MathUtils.distance2D(this, point);
    }

    public double dot(Vector2D point){
        return x * point.x + y * point.y;
    }

    public double angle(){
        return Math.toDegrees(Math.atan2(y,x));
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
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

    public static Vector2D addition(Vector2D v1, Vector2D v2){
        return new Vector2D(v1.x + v2.x, v1.y + v2.y);
    }

    public static Vector2D subtraction(Vector2D v1, Vector2D v2){
        return new Vector2D(v1.x - v2.x, v1.y - v2.y);
    }
}
