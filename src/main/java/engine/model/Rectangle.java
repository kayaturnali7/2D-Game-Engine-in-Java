package engine.model;

import java.awt.Graphics2D;

public class Rectangle extends Shape{
    private final int width;
    private final int height;

    public Rectangle(int width, int height){
        super(width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(this.getColor());
        int x = (int) getX() - (width/2);
        int y = (int) getY() - (height/2);

        g2d.drawRect(x, y, width, height);
    }
}
