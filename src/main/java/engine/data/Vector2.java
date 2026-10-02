package engine.data;

import engine.util.MathUtil;

public class Vector2 {

    private double x;
    private double y;

    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2(){
        this.x = 0;
        this.y = 0;
    }

    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void set(Vector2 vector2){
        this.x = vector2.getX();
        this.y = vector2.getY();
    }

    public void add(Vector2 v){
        this.x += v.x;
        this.y += v.y;
    }

    public void add(double x, double y){
        this.x += x;
        this.y += y;
    }

    public void sub(Vector2 v){
        this.x -= v.x;
        this.y -= v.y;
    }

    public void divide(double x, double y){
        this.x /= x;
        this.y /= y;
    }

    public void scale(double amount){
        this.x *= amount;
        this.y *= amount;
    }

    public double magnitude(){
        return Math.sqrt((x*x) + (y*y) );
    }

    public Vector2 normalize(){
        double mag = magnitude();
        if (mag == 0){
            return new Vector2();
        }
        return new Vector2(x/mag, y/mag);
    }

    public double distance(Vector2 point){
        return MathUtil.distance2D(this, point);
    }

    public double dot(Vector2 point){
        return x * point.x + y * point.y;
    }

    public double angle(){
        return Math.toDegrees(Math.atan2(y,x));
    }

    protected void setX(double x){
        this.x = x;
    }

    protected void setY(double y){
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

    public static Vector2 addition(Vector2 v1, Vector2 v2){
        return new Vector2(v1.x + v2.x, v1.y + v2.y);
    }

    public static Vector2 subtraction(Vector2 v1, Vector2 v2){
        return new Vector2(v1.x - v2.x, v1.y - v2.y);
    }
}
