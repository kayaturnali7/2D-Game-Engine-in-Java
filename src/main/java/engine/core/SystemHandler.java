package engine.core;

import engine.physics.PhysicsEngine;
import engine.graphics.RenderEngine;
import engine.scene.Scene;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

public class SystemHandler {
    private final PhysicsEngine physicsEngine = new PhysicsEngine();
    private final RenderEngine renderEngine = new RenderEngine();

    private final Map<String, Scene> scenes = new HashMap<>();
    private Scene currentScene;


    public SystemHandler(Scene[] scenes){
        for (Scene scene: scenes){
            this.scenes.put(scene.getName(), scene);
        }
        this.currentScene = this.scenes.get("Asteroids Remake");
    }

    public void update(){
        physicsEngine.update(currentScene);
        currentScene.update();
    }

    public void render(Graphics2D g2d){
        renderEngine.render(g2d, currentScene);
    }

    public Scene getCurrentScene(){
        return currentScene;
    }

    public void switchScene(String name){
        currentScene = scenes.get(name);
    }
}
