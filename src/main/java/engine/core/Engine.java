package engine.core;

import engine.scene.Scene;

import java.util.ArrayList;

public class Engine implements Runnable {
    private final Window window;
    private final Panel panel;
    private final Thread gameThread;
    private final Manager manager;

    public Engine(ArrayList<Scene> scenes){
        manager = new Manager(scenes);

        panel = new Panel(manager);
        window = new Window(panel, manager);

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        Scene currentScene = manager.getCurrentScene();
        String sceneName = currentScene.getName();
        int fps = currentScene.getFps();
        double drawInterval = (double) 1000000000 / fps ;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta > 1){
                manager.update();
                window.update(sceneName);
                panel.repaint();
                delta--;
            }
        }
    }
}
