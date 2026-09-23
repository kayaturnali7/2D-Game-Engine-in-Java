package engine.core;

import engine.scene.Scene;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.util.ArrayList;

public class Engine {

    public Engine(ArrayList<Scene> scenes){
        // Create frame, canvas, input, handler
        JFrame frame = new JFrame();
        Canvas canvas = new Canvas();
        InputManager inputManager = new InputManager();
        SystemHandler handler = new SystemHandler(scenes);

        // Get the current scene's properties
        Scene currentScene = handler.getCurrentScene();
        int screenWidth = currentScene.getScreenWidth();
        int screenHeight = currentScene.getScreenHeight();
        String sceneName = currentScene.getName();

        // Add all input listeners
        canvas.addMouseListener(inputManager);
        canvas.addMouseMotionListener(inputManager);
        canvas.addMouseWheelListener(inputManager);
        canvas.addKeyListener(inputManager);

        // Init canvas
        canvas.setPreferredSize(new Dimension(screenWidth, screenHeight));
        canvas.setFocusable(true);

        // Init frame
        frame.setTitle(sceneName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Start run loop
        RunLoop runLoop = new RunLoop(canvas, handler);
        Thread gameLoopThread = new Thread(runLoop);
        gameLoopThread.start();
    }
}
