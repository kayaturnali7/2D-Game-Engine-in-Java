package creator.core;

import creator.display.MainPanel;
import creator.model.Shape;

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
    public static boolean mouseMoving, draggingMouse, holdingLMB, holdingShift, holdingControl;

    private static int mouseX = 0;
    private static int mouseY = 0;

    private MainPanel mainPanel;

    public static int getMouseX() { return mouseX; }
    public static int getMouseY() { return mouseY; }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == mainPanel){
            if (e.getButton() == MouseEvent.BUTTON1){
                if (holdingShift){
                    ShapeController.createNewShape(e.getX(), e.getY());
                } else {
                    ShapeController.addPoint(e.getX(), e.getY());
                }
            } else if (e.getButton() == MouseEvent.BUTTON2){
                Shape hoveredShape = ShapeController.getHoveredShape();
                ShapeController.setSelectedShape(hoveredShape);
            } else{
                if (ShapeController.getHoveredPoint() != null){
                    ShapeController.deletePoint();
                } else{
                    ShapeController.deleteShape(ShapeController.getHoveredShape());
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseButtons[e.getButton()] = true;
        holdingLMB = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseButtons[e.getButton()] = false;
        holdingLMB = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        updateDragged(e);


    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updateMoved(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        updatePressed(e);

        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            ShapeController.toggleBoxVisibility();
        }

        if (e.getKeyCode() == KeyEvent.VK_DELETE){
            ShapeController.deleteShape(ShapeController.getSelected());
        }

        if (e.getKeyCode() == KeyEvent.VK_Z && holdingControl){
            ShapeController.undoDelete();
        }


        if (e.getKeyCode() == KeyEvent.VK_ENTER && holdingShift){
            ShapeController.export();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        updateReleased(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int direction = -e.getWheelRotation();
        ShapeController.incrementBoxSize(direction);
    }

    private void updatePressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length){
            keys[keyCode] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) holdingShift = true;
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) holdingControl = true;
    }

    private void updateReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length){
            keys[keyCode] = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_SHIFT) holdingShift = false;
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) holdingControl = false;
    }

    private void updateMoved(MouseEvent e){
        mouseMoving = true;
        draggingMouse = false;

        mouseX = e.getX();
        mouseY = e.getY();
    }

    private void updateDragged(MouseEvent e){
        draggingMouse = true;

        mouseX = e.getX();
        mouseY = e.getY();

    }

    public void addMainPanel(MainPanel mainPanel){
        this.mainPanel = mainPanel;

    }
}