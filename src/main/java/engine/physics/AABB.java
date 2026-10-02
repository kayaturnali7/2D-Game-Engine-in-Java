package engine.physics;

import engine.data.Vector2;
import engine.geometry.Shape;
import engine.util.MathUtil;

import java.awt.Color;
import java.awt.Graphics2D;

public class AABB {

    private static final Color COLOR = Color.RED;

    private final Shape shape;

    private final Vector2 min = new Vector2();
    private final Vector2 max = new Vector2();

    private int x, y;

    private int width;
    private int height;

    public AABB(Shape shape) {
        this.shape = shape;
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(COLOR);
        g2d.drawRect((int) min.getX(), (int) min.getY(),width,height);
    }

    public void update(){
        int[] verticesX = shape.getVerticesX();
        int[] verticesY = shape.getVerticesY();

        Vector2 min = MathUtil.getMin(verticesX, verticesY);
        Vector2 max = MathUtil.getMin(verticesX, verticesY);

        this.min.set(min);
        this.max.set(max);

        x = (int) min.getX();
        y = (int) min.getY();

        this.width = (int) (max.getX() - min.getX());
        this.height = (int) (max.getY() - min.getY());
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Vector2 getMin(){
        return min;
    }

    public Vector2 getMax(){
        return max;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}