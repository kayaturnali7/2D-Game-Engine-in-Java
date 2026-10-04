package engine.render;

import engine.scene.Scene;
import engine.geometry.*; // keep this wildcard

import java.awt.Graphics2D;

public class RenderSystem {

    public void render(Graphics2D g2d, Scene scene){

    }

    private void drawRect(Graphics2D g2d, Rectangle rect){
        int[] verticesX = rect.getVerticesX();
        int[] verticesY = rect.getVerticesY();
        int numVertices = verticesX.length;

        g2d.drawPolygon(verticesX,verticesY,numVertices);
    }

    private void drawCircle(Graphics2D g2d, Circle circle){
        boolean smooth = circle.isSmooth();

        if (smooth){
            int radius = circle.getRadius();
            int x = (int) (circle.getX() - radius);
            int y = (int) (circle.getY() - radius);
            int diameter = radius*2;

            g2d.drawOval(x,y,diameter,diameter);
        } else {
            int[] verticesX = circle.getVerticesX();
            int[] verticesY = circle.getVerticesY();
            g2d.drawPolygon(verticesX, verticesY, verticesX.length);
        }

    }

    private void updateShape(Shape shape){

        for (int i = 0; i < numVertices; i++) {
            double initialX = baseVertices[i].getX();
            double initialY = baseVertices[i].getY();


            verticesX[i] = (int) (this.x + xPrime);
            verticesY[i] = (int) (this.y + yPrime);

            vertices[i].set(verticesX[i], verticesY[i]);
        }
    }



}
