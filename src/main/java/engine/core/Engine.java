package engine.core;

import engine.scene.Scene;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

public class Engine extends Canvas implements Runnable{

    private final InputManager inputManager;
    private final SystemHandler handler;
    private final JFrame frame;

    private boolean running = true;

    public Engine(Scene[] scenes ) {
        // create and add input
        inputManager = new InputManager();
        this.addMouseListener(inputManager);
        this.addKeyListener(inputManager);

        // create handler and frame
        handler = new SystemHandler(scenes);
        frame = new JFrame();

        // get scene properties
        Scene currentScene = handler.getCurrentScene();
        int screenWidth = currentScene.getScreenWidth();
        int screenHeight = currentScene.getScreenHeight();
        String sceneName = currentScene.getName();

        // set size
        Dimension size = new Dimension(screenWidth, screenHeight);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        this.setFocusable(true);

        // init frame
        frame.setTitle(sceneName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // start loop
        Thread gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    @Override
    public void run() {
        this.createBufferStrategy(2);

        long lastTime = System.nanoTime();
        long lastTimer = System.currentTimeMillis();

        double ns = 1000000000 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1){
                // Update logic here
                handler.update();
                updates++;
                delta--;
            }

            render();
            frames++;

            while (System.currentTimeMillis() - lastTimer > 1000) {
                lastTimer += 1000;
                System.out.println(updates + " ups, " + frames + " fps");
                frames = 0;
                updates = 0;
            }
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        // Render here
        handler.render(g2d);

        g2d.dispose();
        bs.show();
    }
}
