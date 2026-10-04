package engine.render;

import engine.geometry.Shape;

import java.awt.Graphics2D;

public interface RenderComponent {

    default void render(Graphics2D g2d){
        Shape shape = getShape();
        g2d.setColor(shape.getColor());
        shape.draw(g2d);
    }



    Shape getShape();
}
