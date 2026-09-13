package creator.model;

import creator.core.Input;
import creator.core.ShapeController;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Shape {
    private String name;
    private final Color color = Color.WHITE;

    private int screenX;
    private int screenY;

    private Point centerPoint;

    private int offsetX;
    private int offsetY;

    private java.awt.Shape shape;
    private Rectangle2D bounds;

    private final ArrayList<Point> points = new ArrayList<>();
    private Point currentPoint;

    private boolean mouseHovered = false;
    private boolean canDrag = false;
    private boolean dragging = false;

    private int hitboxSize = 50;
    private boolean drawHitboxes = true;

    private boolean current = false;
    private boolean alive = true;

    public Shape(int x, int y) {

        screenX = x;
        screenY = y;

        int side = 100;
        addPoint(x, y-side);
        addPoint(x-side, y+side);
        addPoint(x+side, y+side);

        currentPoint = points.get(0);
        updateShape();
        updateCenter();
    }

    public void update() {
        updatePoints();
        updateShape();
        updateCenter();
        updateState();

        if (mouseHovered && !dragging){
            updateOffsets();
        }
        if (canDrag && dragging){
            moveToMouse();
        }
    }

    public void draw(Graphics2D g2d) {
        drawBounds(g2d);
        drawShape(g2d);
        drawPoints(g2d);
        centerPoint.draw(g2d, true);
    }

    private void drawShape(Graphics2D g2d) {
        if (shape == null) return;

        g2d.setColor(color);
        g2d.draw(shape);
    }

    private void drawPoints(Graphics2D g2d) {
        for (Point point : points) {
            point.draw(g2d, drawHitboxes);
        }
    }

    private void drawBounds(Graphics2D g2d){
        if (bounds == null) return;

        if (drawHitboxes && current || !current && mouseHovered) {
            g2d.setColor(Color.green);
            g2d.draw(bounds);
        }
    }

    private void updatePoints() {
        for (Point point : points) {
            if (point.isMouseHovered()){
                currentPoint = point;
            }
            point.update(hitboxSize);
        }
        points.removeIf(point -> !point.isAlive());
    }

    private void updateShape() {
        if (points.isEmpty()) return;

        int numPoints = points.size();
        int[] xPoints = new int[numPoints];
        int[] yPoints = new int[numPoints];

        int index = 0;
        for (Point point : points) {
            xPoints[index] = point.getScreenX() + (Point.SIZE/ 2);
            yPoints[index] = point.getScreenY() + (Point.SIZE/ 2);
            index += 1;
        }

        shape = new Polygon(xPoints, yPoints, numPoints);
        bounds = shape.getBounds2D();
    }

    public void addPoint(int X, int Y) {
        points.add(new Point(X, Y, this));
    }

    private void moveToMouse(){
        screenX = Input.getMouseX() - offsetX;
        screenY = Input.getMouseY() - offsetY;
    }

    private void updateCenter(){
        if (bounds == null) return;

        int centerX = (int) bounds.getCenterX();
        int centerY = (int) bounds.getCenterY();

        centerPoint = new Point(centerX,centerY,this);
    }

    public void toggleDrawBoxes() {
        drawHitboxes = !drawHitboxes;
    }

    public void increaseBoxSize() {
        if (hitboxSize >= ShapeController.MAX_BOX_SIZE) return;
        hitboxSize += ShapeController.SIZE_INCREMENT;
    }

    public void decreaseBoxSize() {
        if (hitboxSize <= 0) return;
        hitboxSize -= ShapeController.SIZE_INCREMENT;
    }

    private void updateState(){
        current = ShapeController.getSelected() == this;

        if (bounds == null) return;
        mouseHovered = bounds.contains(Input.getMouseX(), Input.getMouseY());

        if (mouseHovered){
            ShapeController.setHoveredShape(this);
        }

        dragging = mouseHovered && Input.holdingLMB;
        canDrag = ShapeController.getHoveredPoint() == null;

    }

    private void updateOffsets(){
        offsetX = Input.getMouseX()-screenX;
        offsetY = Input.getMouseY()-screenY;
    }


    public void kill(){
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void revive(){
        alive = true;
    }

    public int getScreenX(){
        return screenX;
    }

    public int getScreenY(){
        return screenY;
    }

    public ArrayList<Point> getPoints(){
        return points;
    }

    public boolean isDrawingBoxes(){
        return drawHitboxes;
    }
}
