package engine.geometry;

import java.awt.Color;
import java.awt.Graphics2D;

public class Circle extends Shape {

    private final int radius;
    private final boolean smooth;

    public Circle(int radius, Color color) {
        super(radius, color);
        this.radius = radius;
        this.smooth = true;
    }

    public Circle(int radius, Color color, boolean smooth) {
        super(radius, color);
        this.radius = radius;
        this.smooth = smooth;
    }

    @Override
    public void onDraw(Graphics2D g2d){


    }

    public boolean isSmooth(){
        return smooth;
    }

    public int getRadius() {
        return radius;
    }
}
