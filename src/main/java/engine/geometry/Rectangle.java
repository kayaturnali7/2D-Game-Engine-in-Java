package engine.geometry;

import java.awt.Color;
import java.awt.Graphics2D;

public class Rectangle extends Shape{
    protected int width;
    protected int height;

    public Rectangle(int width, int height, Color color){
        super(width, height, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void onDraw(Graphics2D g2d) {
        g2d.drawPolygon(verticesX,verticesY,numVertices);
    }
}
