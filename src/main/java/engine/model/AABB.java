package engine.model;

import java.awt.Color;
import java.awt.Graphics2D;

public class AABB {

    private static final Color COLOR = Color.RED;

    private final Shape shape;

    private int minX;
    private int minY;

    private int width;
    private int height;

    public AABB(Shape shape) {
        this.shape = shape;
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(COLOR);
        g2d.drawRect(minX,minY,width,height);
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

        this.minX = minX;
        this.minY = minY;

        this.width = maxX - minX;
        this.height = maxY - minY;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getX(){
        return minX;
    }

    public int getY(){
        return minY;
    }
}