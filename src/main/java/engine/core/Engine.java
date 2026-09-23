package engine.core;

import engine.scene.Scene;

import java.awt.Dimension;
import java.util.ArrayList;

public class Engine implements Runnable {
    private final SystemHandler systemHandler;
    private final Thread gameThread;

    private final Window window;
    private final Panel panel;

    public Engine(ArrayList<Scene> scenes){
        systemHandler = new SystemHandler(scenes);

        panel = new Panel(systemHandler);
        window = new Window(panel, systemHandler);

        gameThread = new Thread(this);
        gameThread.start();
    }

    private void updateSystems(Scene currentScene){
        int screenWidth = currentScene.getScreenWidth();
        int screenHeight = currentScene.getScreenHeight();
        String sceneName = currentScene.getName();

        panel.setPreferredSize(new Dimension(screenWidth, screenHeight));
        panel.revalidate();

        window.revalidate();
        window.pack();

        panel.repaint();
        window.setTitle(sceneName);
    }

    @Override
    public void run() {

        Scene currentScene;
        int fps;
        double drawInterval;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentScene = systemHandler.getCurrentScene();
            fps = currentScene.getFps();

            drawInterval = (double) 1000000000 / fps;
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta > 1){
                systemHandler.update();
                updateSystems(currentScene);

                delta--;
            }
        }
    }
}
