package shapecreator.core;

import shapecreator.display.MainPanel;

public class RunLoop implements Runnable {
    private static final int FPS = 60;
    private Thread mainThread;
    private final MainPanel mainPanel;

    public RunLoop(MainPanel mainPanel){
        this.mainPanel = mainPanel;
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (mainThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta > 1){

                ShapeController.update();
                mainPanel.repaint();

                delta--;
            }
        }
    }

    public void start(){
        mainThread = new Thread(this);
        mainThread.start();
    }

}
