package engine.graphics;

import engine.entity.Entity;
import engine.scene.Scene;
import engine.ui.UserInterface;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class RenderEngine {

    public void render(Graphics2D g2d, Scene scene){
        drawEntities(g2d, scene);
    }

    private void drawEntities(Graphics2D g2d, Scene scene){
        ArrayList<Entity> entities = scene.getEntities();
        ArrayList<UserInterface> userInterfaces = scene.getUserInterfaces();

        for (Entity entity : entities){
            entity.draw(g2d);
        }

        for (UserInterface userInterface : userInterfaces){
            userInterface.draw(g2d);
        }
    }
}
