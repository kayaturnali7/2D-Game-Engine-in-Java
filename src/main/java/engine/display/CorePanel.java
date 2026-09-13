package engine.display;

import engine.core.Handler;
import engine.core.Input;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class CorePanel extends JPanel{
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 800;
    public static final Color BG_COLOR = Color.black;

    private final Handler handler;

    public CorePanel(Handler handler, Input input){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(BG_COLOR);
        this.setFocusable(true);
        this.setDoubleBuffered(true);

        this.handler = handler;

        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        this.addKeyListener(input);
        this.addMouseWheelListener(input);
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        handler.draw(g2d);
    }
}
