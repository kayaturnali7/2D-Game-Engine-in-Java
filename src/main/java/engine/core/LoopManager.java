package engine.core;

import engine.components.AudioEngine;
import engine.components.GameWorld;
import engine.components.PhysicsEngine;
import engine.components.RenderingEngine;

import java.awt.Graphics2D;

public class LoopManager {
    private final GameWorld gameWorld = new GameWorld();
    private final PhysicsEngine physicsEngine = new PhysicsEngine();
    private final AudioEngine audioEngine = new AudioEngine();

    private final RenderingEngine renderingEngine = new RenderingEngine();

    public void update(){
        gameWorld.update();
        physicsEngine.update();
        audioEngine.update();
    }

    public void draw(Graphics2D g2d){
        renderingEngine.render(g2d);
    }
}
