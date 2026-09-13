package shapecreator.display;

import shapecreator.core.Input;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;

public class Panel extends JPanel{

    public Panel(Input input, int width, int height, Color color){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(color);
        this.setFocusable(true);
        this.setDoubleBuffered(true);

        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        this.addKeyListener(input);
        this.addMouseWheelListener(input);
    }
}
