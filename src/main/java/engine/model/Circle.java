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
        int x = (int) this.getX();
        int y = (int) this.getY();
        Color color = this.getColor();

        g2d.setColor(color);
        g2d.drawOval(x-radius/2,y-radius/2,radius,radius);
    }

    public int getRadius() {
        return radius;
    }
}
