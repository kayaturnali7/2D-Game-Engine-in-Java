package engine.geometry;

import engine.util.GeometryUtil;

import java.awt.Color;

public class Circle extends Shape {

    private final int radius;

    public Circle(int radius, int resolution, Color color) {
        super(GeometryUtil.generateCircleVertices(radius, resolution), color);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
}
