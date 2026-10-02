package engine.geometry;

import engine.data.Vector2;

import java.awt.Color;
import java.awt.Graphics2D;

public class ConvexPolygon extends Shape {

    public ConvexPolygon(Vector2[] vertices, Color color) {
        super(vertices, color);
    }

    @Override
    public void onDraw(Graphics2D g2d) {
        g2d.drawPolygon(verticesX, verticesY, numVertices);
    }
}
