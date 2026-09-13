package engine.core;

public class CoreLoop implements Runnable{
    public static final int FPS = 60;
    private Thread gameThread;

    private final CorePanel panel;
    private final CoreManager coreManager;

    public CoreLoop(CorePanel panel, CoreManager coreManager){
        this.panel = panel;
        this.coreManager = coreManager;
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

                coreManager.update();
                panel.repaint();

                delta--;
            }
        }
    }
}
