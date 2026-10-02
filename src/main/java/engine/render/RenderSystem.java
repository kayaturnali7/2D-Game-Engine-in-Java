package engine.render;

import engine.scene.Scene;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class RenderSystem {

    public void render(Graphics2D g2d, Scene scene){
        drawScene(g2d, scene);
    }

    private void drawScene(Graphics2D g2d, Scene scene){
        ArrayList<RenderComponent> renderComponents = scene.getRenderComponents();

        for (RenderComponent renderComponent : renderComponents){
            renderComponent.render(g2d);
        }
    }
}
