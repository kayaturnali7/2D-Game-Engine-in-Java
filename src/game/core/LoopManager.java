package game.core;

import game.engine.AudioEngine;
import game.engine.GameWorld;
import game.engine.PhysicsEngine;
import game.engine.RenderingEngine;

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
