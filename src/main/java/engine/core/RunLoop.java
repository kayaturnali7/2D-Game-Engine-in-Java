package engine.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

public class RunLoop implements Runnable{

    private final Canvas canvas;
    private final SystemHandler handler;

    private boolean  running = true;

    public RunLoop(Canvas canvas, SystemHandler handler) {
        this.canvas = canvas;
        this.handler = handler;
    }

    @Override
    public void run() {
        canvas.createBufferStrategy(2);
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1){
                // Update logic here
                handler.update();
                delta--;
            }

            Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

            // Render here
            handler.render(g2d);

            g2d.dispose();
            bufferStrategy.show();
        }
    }
}
