package engine.core;

import engine.physics.PhysicsEngine;
import engine.graphics.RenderEngine;
import engine.scene.Scene;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Manager {
    private final PhysicsEngine physicsEngine = new PhysicsEngine();
    private final RenderEngine renderEngine = new RenderEngine();

    private ArrayList<Scene> scenes = new ArrayList<>();
    private Scene currentScene;


    public Manager(ArrayList<Scene> scenes){
        this.scenes.addAll(scenes);
        currentScene = this.scenes.get(0);
    }

    public void update(){
        physicsEngine.update(currentScene);
        currentScene.update();
    }

    public void draw(Graphics2D g2d){
        renderEngine.render(g2d, currentScene);
    }

    public Scene getCurrentScene(){
        return currentScene;
    }


}
