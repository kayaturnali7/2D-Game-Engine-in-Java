package engine.core;

import engine.components.GameWorld;
import engine.physics.PhysicsEngine;
import engine.components.RenderingEngine;

import java.awt.Graphics2D;

public class CoreHandler {
    private final GameWorld gameWorld = new GameWorld();
    private final PhysicsEngine physicsEngine = new PhysicsEngine();
    private final RenderingEngine renderingEngine = new RenderingEngine();

    public void update(){
        gameWorld.update();
        physicsEngine.update();
    }

    public void draw(Graphics2D g2d){
        renderingEngine.render(g2d);
    }
}
