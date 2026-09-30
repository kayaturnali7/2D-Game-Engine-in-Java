package engine.model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Polygon extends Shape {


    public Polygon(Vector2D[] vertices, Color color) {
        super(vertices, color);
    }


    @Override
    public void onDraw(Graphics2D g2d) {
        g2d.setColor(this.getColor());
        g2d.drawPolygon(verticesX, verticesY, numVertices);
    }
}
