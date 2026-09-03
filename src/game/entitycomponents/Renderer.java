package game.entitycomponents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

public class Renderer {
    private final Entity currentEntity;

    private boolean drawBoundaryBox;
    private boolean fill;
    private final Color color;

    protected Renderer(Entity entity, Color color){
        currentEntity = entity;
        this.color = color;
    }

    protected void draw(Graphics2D g2d){
        Shape shape = currentEntity.getGeometry().shape();
        if (shape == null) return;

        g2d.setColor(color);
        if (!fill) {g2d.draw(shape);} else {g2d.fill(shape);}

        if (drawBoundaryBox){
            Shape bounds = currentEntity.getGeometry().bounds();
            g2d.setColor(Color.RED);
            g2d.draw(bounds);
        }
    }

    protected void setDrawBoundaryBox(boolean value){
        drawBoundaryBox = value;
    }

    protected void setFill(boolean value){
        fill = value;
    }
}
