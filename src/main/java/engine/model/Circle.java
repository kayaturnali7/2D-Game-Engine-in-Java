package engine.model;


import java.awt.Graphics2D;

public class Circle extends Shape {

    private final int radius;

    public Circle(int radius) {
        super(radius);
        this.radius = radius;
    }

    @Override
    public void draw(Graphics2D g2d){
//

        g2d.setColor(this.getColor());
        g2d.drawPolygon(xPoints, yPoints, numPoints);
    }

    public int getRadius() {
        return radius;
    }
}
