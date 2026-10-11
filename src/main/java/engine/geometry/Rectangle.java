package engine.geometry;

import java.awt.Color;

public class Rectangle extends Shape {
    protected final int width;
    protected final int height;

    public Rectangle(int width, int height, Color color) {
        super(new int[][]{
                {-width, -width, width, width},
                {height, -height, -height, height}
        },color);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
