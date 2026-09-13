package engine.components;

import engine.ecs.Entity;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class RenderingEngine {
    private ArrayList<Entity> entities = GameWorld.getEntities();


    public void render(Graphics2D g2d){
        entities = GameWorld.getEntities();
        drawEntities(g2d);
    }

    private void drawEntities(Graphics2D g2d){
        for (Entity entity : entities){
            entity.draw(g2d);
        }
    }
}
