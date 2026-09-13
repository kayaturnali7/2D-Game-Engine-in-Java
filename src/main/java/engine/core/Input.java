package engine.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Input implements MouseListener, MouseMotionListener, KeyListener, MouseWheelListener{

    public static final boolean[] keys = new boolean[256];
    public static final boolean[] mouseButtons = new boolean[8];

    private static int mouseX = 0;
    private static int mouseY = 0;

    public static int getMouseX() { return mouseX; }
    public static int getMouseY() { return mouseY; }

    public static boolean isKeyPressed(int keyCode){
        if (keyCode >= 0 && keyCode < keys.length){
            return keys[keyCode];
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseButtons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseButtons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length){
            keys[keyCode] = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length){
            keys[keyCode] = false;
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}