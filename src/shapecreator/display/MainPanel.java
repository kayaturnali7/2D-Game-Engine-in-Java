package shapecreator.display;

import shapecreator.core.Input;
import shapecreator.ShapeCreatorLauncher;
import shapecreator.core.ShapeController;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class MainPanel extends Panel{
    public MainPanel(Input input) {
        super(input, ShapeCreatorLauncher.MAIN_PANEL_WIDTH, ShapeCreatorLauncher.SCREEN_HEIGHT, ShapeCreatorLauncher.MAIN_PANEL_COLOR);
        this.setLocation(0,0);
        input.addMainPanel(this);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        ShapeController.draw(g2d);
    }
}
