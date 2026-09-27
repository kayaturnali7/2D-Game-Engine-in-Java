package engine.model;

import java.awt.Graphics2D;

public class Polygon extends Shape {

    private final Point[] vertices;

    private final int numPoints;
    private final int[] xPoints;
    private final int[] yPoints;

    public Polygon(Point[] vertices) {
        this.vertices = vertices;

        this.numPoints = vertices.length;
        this.xPoints = new int[numPoints];
        this.yPoints = new int[numPoints];

        build();
    }

    @Override
    public void update(double x, double y, double direction) {
        moveTo(x,y);
        rotateTo(direction-90);
        updateVertices();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(this.getColor());
        g2d.drawPolygon(xPoints, yPoints, numPoints);
    }


    private void build() {
        for (int i = 0; i < numPoints; i++){
            int x = (int) (vertices[i].getX());
            int y = (int) (vertices[i].getY());

            xPoints[i] = x;
            yPoints[i] = y;
        }
    }

    private void updateVertices() {
        double cos = Math.cos(Math.toRadians(-getDirection()));
        double sin = Math.sin(Math.toRadians(-getDirection()));

        for (int i = 0; i < numPoints; i++) {
            double originalX = vertices[i].getX();
            double originalY = vertices[i].getY();

            // apply rotation matrix
            double xPrime = originalX * cos - originalY * sin;
            double yPrime = originalX * sin + originalY * cos;

            xPoints[i] = (int) (this.getX() + xPrime);
            yPoints[i] = (int) (this.getY() + yPrime);
        }
    }

    public Point[] getVertices(){
        return this.vertices;
    }

    public Point getVertex(int index){
        return new Point(xPoints[index], yPoints[index]);
    }
}
