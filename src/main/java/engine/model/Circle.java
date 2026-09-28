package engine.model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Circle extends Shape {
    private final int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw(Graphics2D g2d){
        int x = (int) this.getX() - (radius/2);
        int y = (int) this.getY() - (radius/2);

        g2d.setColor(this.getColor());
        g2d.drawOval(x,y,radius,radius);
    }

    public int getRadius() {
        return radius;
    }
}
