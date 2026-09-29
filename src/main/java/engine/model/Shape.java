package engine.model;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Shape {

    private double x;
    private double y;
    private double angle;
    private final Color color;

    private final Point[] originalVertices;

    private final Point[] vertices;

    protected final int numPoints;
    protected final int[] xPoints;
    protected final int[] yPoints;

    //private final Rectangle AABB;

    public Shape(Point[] vertices) {
        this.color = Color.WHITE;
        this.numPoints = vertices.length;
        this.originalVertices = vertices;

        this.vertices = new Point[numPoints];
        for (int i = 0; i < numPoints; i++) {
            this.vertices[i] = new Point();
        }

        this.xPoints = new int[numPoints];
        this.yPoints = new int[numPoints];

        for (int i = 0; i < numPoints; i++){
            int x = (int) (originalVertices[i].getX());
            int y = (int) (originalVertices[i].getY());

            xPoints[i] = x;
            yPoints[i] = y;
        }
    }

    public Shape(int radius){
        this.color = Color.WHITE;
        this.numPoints = 32;
        this.originalVertices = new Point[numPoints];

        for (int i = 1; i < numPoints-1; i++){
            double x = radius * Math.cos(2 * Math.PI * i/32);
            double y = radius * Math.sin(2 * Math.PI * i/32);
            originalVertices[i] = new Point(x,y);
        }

        this.vertices = originalVertices;

        this.xPoints = new int[numPoints];
        this.yPoints = new int[numPoints];

        for (int i = 0; i < numPoints; i++){
            int x = (int) (originalVertices[i].getX());
            int y = (int) (originalVertices[i].getY());

            xPoints[i] = x;
            yPoints[i] = y;
        }
    }

    public Shape(int width, int height){
        this.color = Color.WHITE;

        this.originalVertices = new Point[]{
                new Point((double) width/2, (double) height/2),
                new Point((double) -width/2, (double) height/2),
                new Point((double) -width/2, (double) -height/2),
                new Point((double) width/2, (double) -height/2),
        };
        this.numPoints = originalVertices.length;

        this.vertices = new Point[numPoints];
        for (int i = 0; i < numPoints; i++) {
            this.vertices[i] = new Point();
        }

        this.xPoints = new int[numPoints];
        this.yPoints = new int[numPoints];

        for (int i = 0; i < numPoints; i++){
            int x = (int) (originalVertices[i].getX());
            int y = (int) (originalVertices[i].getY());

            xPoints[i] = x;
            yPoints[i] = y;
        }
    }

    public void update(double x, double y, double direction){
        moveTo(x,y);
        rotateTo(direction-90);
        updateVertices();
    }


    private void updateVertices() {
        double cos = Math.cos(Math.toRadians(-getDirection()));
        double sin = Math.sin(Math.toRadians(-getDirection()));

        for (int i = 0; i < numPoints; i++) {
            double originalX = originalVertices[i].getX();
            double originalY = originalVertices[i].getY();

            // apply rotation matrix
            double xPrime = originalX * cos - originalY * sin;
            double yPrime = originalX * sin + originalY * cos;

            xPoints[i] = (int) (this.getX() + xPrime);
            yPoints[i] = (int) (this.getY() + yPrime);

            vertices[i].setLocation(xPoints[i], yPoints[i]);
        }
    }


    public abstract void draw(Graphics2D g2d);

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Color getColor() {
        return this.color;
    }

    public double getDirection() {
        return this.angle;
    }

    public void rotateTo(double angle){
        this.angle = angle;
    }

    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public Point[] getVertices(){
        return this.vertices;
    }

    public Point getVertex(int index){
        return vertices[index];
    }

    public String toString() {
        return "(" + getX() + "," + getY() + ") " + getDirection();
    }
}
