package engine.model;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Shape {

    private final Vector2D[] baseVertices;

    private final Color color;

    private double x, y;
    private double angle;

    private Vector2D[] vertices;
    protected int[] verticesX, verticesY;
    protected int numVertices;

    private final AABB AABB = new AABB(this);

    public Shape(Vector2D[] vertices, Color color) {
        this.color = color;
        baseVertices = vertices;

        build();
    }

    public Shape(int radius, Color color) {
        this.color = color;

        int n = 16; // amount of points
        baseVertices = new Vector2D[n];

        for (int i = 1; i < n + 1; i++ ){
            double x = radius * Math.cos(2 * Math.PI * i / n);
            double y = radius * Math.sin(2 * Math.PI * i / n);

            baseVertices[i-1] = new Vector2D(x,y);
        }

        build();
    }

    public Shape(double width, double height, Color color){
        this.color = color;

        Vector2D topLeft = new Vector2D(-width/2, height/2);
        Vector2D topRight = new Vector2D(width/2, height/2);
        Vector2D bottomRight = new Vector2D(width/2, -height/2);
        Vector2D bottomLeft = new Vector2D(-width/2, -height/2);

        baseVertices = new Vector2D[]{topLeft, bottomLeft, bottomRight, topRight };

        build();
    }

    private void build(){
        numVertices = baseVertices.length;

        vertices = new Vector2D[numVertices];
        for (int i = 0; i < numVertices; i++){
            vertices[i] = new Vector2D();
        }

        verticesX = new int[numVertices];
        verticesY = new int[numVertices];

        for (int i = 0; i < numVertices; i++){
            int x = (int) baseVertices[i].getX();
            int y = (int) baseVertices[i].getY();

            verticesX[i] = x;
            verticesY[i] = y;
        }
    }

    public void update(double x, double y, double direction){
        moveTo(x,y);
        rotateTo(direction-90); //must subtract 90 from direction !!!!
        updateVertices();
        AABB.update();
    }

    public void draw(Graphics2D g2d){
        onDraw(g2d);
        //AABB.draw(g2d);
    }

    private void updateVertices() {
        double cos = Math.cos(Math.toRadians(-angle));
        double sin = Math.sin(Math.toRadians(-angle));

        for (int i = 0; i < numVertices; i++) {
            double originalX = baseVertices[i].getX();
            double originalY = baseVertices[i].getY();

            // apply rotation matrix
            double xPrime = originalX * cos - originalY * sin;
            double yPrime = originalX * sin + originalY * cos;

            verticesX[i] = (int) (this.x + xPrime);
            verticesY[i] = (int) (this.y + yPrime);

            vertices[i].setLocation(verticesX[i], verticesY[i]);
        }
    }


    public abstract void onDraw(Graphics2D g2d);

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Color getColor() {
        return this.color;
    }

    public double getAngle() {
        return this.angle;
    }

    public void rotateTo(double angle){
        this.angle = angle;
    }

    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D getVertex(int vertex){
        return vertices[vertex];
    }

    public AABB getAABB(){
        return AABB;
    }
}
