package engine.entity;

import java.awt.Graphics2D;
import engine.model.Shape;

public class Renderer {
    private final Entity entity;

    protected Renderer(Entity entity){
        this.entity = entity;
    }

    protected void draw(Graphics2D g2d){
        Shape shape = entity.getShape();
        if (shape == null) return;

        g2d.setColor(shape.getColor());
        shape.draw(g2d);
    }
}
