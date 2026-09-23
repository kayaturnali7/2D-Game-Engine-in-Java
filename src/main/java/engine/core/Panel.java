package engine.core;

import engine.scene.Scene;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Panel extends JPanel{
    private final SystemHandler systemHandler;

    public Panel(SystemHandler systemHandler){
        Scene currentScene = systemHandler.getCurrentScene();

        int screenWidth = currentScene.getScreenWidth();
        int screenHeight = currentScene.getScreenHeight();
        Color bgColor = currentScene.getBgColor();

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(bgColor);

        this.setFocusable(true);
        this.setDoubleBuffered(true);

        Input input = new Input();
        this.systemHandler = systemHandler;

        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        this.addKeyListener(input);
        this.addMouseWheelListener(input);
    }

    @Override
    protected void paintComponent(Graphics g){
        Scene currentScene = systemHandler.getCurrentScene();
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color bgColor = currentScene.getBgColor();
        g2d.setBackground(bgColor);

        systemHandler.draw(g2d);
    }
}
