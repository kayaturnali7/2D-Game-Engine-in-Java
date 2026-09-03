package shapecreator.model;

import shapecreator.core.Input;
import shapecreator.core.ShapeController;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;

public class Point {
    public final static int SIZE = 7;
    private final static Color COLOR = Color.RED;
    private final static Color HITBOX_COLOR = Color.RED;

    private int screenX;
    private int screenY;

    private int localX;
    private int localY;

    private int hitboxSize;
    private Rectangle2D hitbox;

    private boolean mouseHovered = false;
    private boolean alive = true;

    private final Shape shape;

    public Point(int X, int Y, Shape shape ){
        screenX = X;
        screenY = Y;

        localX = shape.getScreenX() - screenX;
        localY = shape.getScreenY() - screenY;

        this.shape = shape;
        updateHitbox();
    }

    private void updateHitbox(){
        double x = screenX -(((double) hitboxSize / 2) - ((double) SIZE / 2));
        double y = screenY - (((double) hitboxSize / 2) - ((double) SIZE / 2));

        hitbox = new Rectangle2D.Double(x, y, hitboxSize, hitboxSize);
    }

    protected void update(int hitboxSize){
        this.hitboxSize = hitboxSize;
        updateState();
        if (mouseHovered && Input.draggingMouse){
            moveToMouse();
        }
        updateScreenPosition();
        updateLocalPosition();
        updateHitbox();
    }

    protected void draw(Graphics2D g2d, boolean drawBox){
        g2d.setColor(COLOR);
        g2d.fillOval(screenX, screenY, SIZE, SIZE);
        drawHitbox(g2d, drawBox);
    }

    private void drawHitbox(Graphics2D g2d, boolean drawBox){
        if(drawBox){
            g2d.setColor(HITBOX_COLOR);
            g2d.draw(hitbox);
        }
    }

    private void updateLocalPosition(){
        localX = shape.getScreenX() - screenX;
        localY = shape.getScreenY() - screenY;
    }

    private void updateState(){
        mouseHovered = hitbox.contains(Input.getMouseX(), Input.getMouseY());
        if (mouseHovered){
            ShapeController.setHoveredPoint(this);
        }
    }

    protected void moveToMouse(){
        screenX = Input.getMouseX();
        screenY = Input.getMouseY();
        updateLocalPosition();
    }

    private void updateScreenPosition(){
        screenX = shape.getScreenX() - localX;
        screenY = shape.getScreenY() - localY;
    }

    protected int getScreenX(){
        return screenX;
    }

    protected int getScreenY(){
        return screenY;
    }

    public boolean isMouseHovered(){
        return mouseHovered;
    }

    public boolean isAlive(){
        return alive;
    }

    public void kill(){
        alive = false;
    }

    public int getLocalX(){
        return localX;
    }

    public int getLocalY(){
        return localY;
    }
}
