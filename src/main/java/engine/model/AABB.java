package engine.model;

import java.awt.Color;
import java.awt.Graphics2D;

public class AABB {

    private static final Color COLOR = Color.RED;

    private final Shape shape;

    private final Vector2D min = new Vector2D();
    private final Vector2D max = new Vector2D();

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
        int[] verticesX = shape.verticesX;
        int[] verticesY = shape.verticesY;
        int numVertices = shape.numVertices;

        int minX = verticesX[0];
        int maxX = verticesX[0];

        int minY = verticesY[0];
        int maxY = verticesY[0];

        for (int i = 1; i < numVertices; i++){
            int currentX = verticesX[i];
            int currentY = verticesY[i];

            if (currentX < minX){
                minX = currentX;
            }

            //maxX
            if (currentX > maxX){
                maxX = currentX;
            }

            //minY
            if (currentY < minY){
                minY = currentY;
            }

            //maxY
            if (currentY > maxY){
                maxY = currentY;
            }
        }

        min.setLocation(minX, minY);
        max.setLocation(maxX, maxY);

        x = minX;
        y = minY;

        this.width = maxX - minX;
        this.height = maxY - minY;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Vector2D getMin(){
        return min;
    }

    public Vector2D getMax(){
        return max;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}