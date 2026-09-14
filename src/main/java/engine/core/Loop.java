package engine.core;

import engine.display.CorePanel;

public class Loop implements Runnable{
    public static final int FPS = 60;
    private Thread gameThread;

    private final CorePanel panel;
    private final Handler handler;

    public Loop(CorePanel panel, Handler handler){
        this.panel = panel;
        this.handler = handler;
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
                handler.update();
                panel.repaint();

                delta--;
            }
        }
    }
}
