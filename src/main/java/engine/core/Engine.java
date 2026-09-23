package engine.core;

import engine.scene.Scene;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.util.ArrayList;

public class Engine {

    private final InputManager inputManager;
    private final SystemHandler handler;
    private final JFrame frame;
    private final Canvas canvas;
    private final RunLoop runLoop;

    public Engine(ArrayList<Scene> scenes){
        inputManager = new InputManager();
        handler = new SystemHandler(scenes);

        // Create frame and canvas
        frame = new JFrame();
        canvas = new Canvas();

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
        runLoop = new RunLoop(canvas, handler);
        Thread gameLoopThread = new Thread(runLoop);
        gameLoopThread.start();
    }
}
