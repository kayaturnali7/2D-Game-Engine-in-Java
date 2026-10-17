package engine.data;

import engine.util.MathUtil;

public class Vector2 {

    public double x;
    public double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public void add(Vector2 v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void add(double x, double y) {
        this.x += v.x;
        this.y += v.y;
    }


    public void sub(Vector2 v){
        this.x -= v.x;
        this.y -= v.y;
    }

    public void scale(double amount) {
        this.x *= amount;
        this.y *= amount;
    }

    public double magnitude() {
        return Math.sqrt((x*x) + (y*y) );
        
    }

    public Vector2 normalize() {
        double mag = magnitude();
        if (mag == 0){
            System.out.println("Cannot normalize");
            return new Vector2(x, y);
        }
        return new Vector2(x/mag, y/mag);
    }

    public double distance(Vector2 point) {
        return MathUtil.distance2D(this, point);
    }

    public double dot(Vector2 point) {
        return x * point.x + y * point.y;
    }

    public double angle() {
        return Math.toDegrees(Math.atan2(y,x));
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
