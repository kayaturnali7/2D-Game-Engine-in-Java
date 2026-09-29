package engine.model;

import java.awt.Graphics2D;

public class Polygon extends Shape {


    public Polygon(Point[] vertices) {
        super(vertices);
    }


    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(this.getColor());
        g2d.drawPolygon(xPoints, yPoints, numPoints);
    }
}
