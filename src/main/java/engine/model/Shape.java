package engine.model;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Shape {

    private double x;
    private double y;

    private double direction;

    private final Color color;

    public Shape() {
        this.color = Color.WHITE;
    }

    public void update(double x, double y, double direction){
        moveTo(x,y);
        rotateTo(direction);
    }

    public abstract void draw(Graphics2D g2d);

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Color getColor() {
        return this.color;
    }

    public double getDirection() {
        return this.direction;
    }

    public void rotateTo(double angle){
        direction = angle;
    }

    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + getX() + "," + getY() + ") " + getDirection();
    }
}
