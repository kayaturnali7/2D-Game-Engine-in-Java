package engine.model;

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
        if (smooth){
            int x = (int) getX()-radius;
            int y = (int) getY()-radius;

            g2d.drawOval(x,y,radius*2,radius*2);
        } else g2d.drawPolygon(verticesX, verticesY, numVertices);

    }

    public int getRadius() {
        return radius;
    }
}
