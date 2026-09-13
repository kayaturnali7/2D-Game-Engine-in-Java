package engine.gfx;

import engine.ecs.Entity;
import engine.scene.Scene;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class RenderEngine {

    public void render(Graphics2D g2d, Scene scene){
        drawEntities(g2d, scene);
    }

    private void drawEntities(Graphics2D g2d, Scene scene){
        ArrayList<Entity> entities = scene.getEntities();
        for (Entity entity : entities){
            entity.draw(g2d);
        }
    }
}
