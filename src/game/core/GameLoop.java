package game.core;

import game.display.GamePanel;

// test

public class GameLoop implements Runnable{
    public static final int FPS = 60;
    private Thread gameThread;

    private final GamePanel panel;
    private final LoopManager loopManager;

    public GameLoop(GamePanel panel, LoopManager loopManager){
        this.panel = panel;
        this.loopManager = loopManager;
    }

    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta > 1){

                loopManager.update();
                panel.repaint();

                delta--;
            }
        }
    }
}
