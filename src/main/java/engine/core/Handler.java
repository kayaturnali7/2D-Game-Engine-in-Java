package engine.core;

import engine.physics.PhysicsEngine;
import engine.gfx.RenderEngine;
import engine.scene.Scene;
import engine.scene.SceneManager;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Handler {
    private final SceneManager sceneManager = new SceneManager();
    private final PhysicsEngine physicsEngine = new PhysicsEngine();
    private final RenderEngine renderEngine = new RenderEngine();

    public Handler(ArrayList<Scene> scenes){
        sceneManager.addScenes(scenes);
    }

    public void update(){
        Scene currentScene = sceneManager.getCurrentScene();

        physicsEngine.update(currentScene);
        currentScene.update();
    }

    public void draw(Graphics2D g2d){
        Scene currentScene = sceneManager.getCurrentScene();

        renderEngine.render(g2d, currentScene);
    }
}
