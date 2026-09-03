package shapecreator;

import shapecreator.core.Input;
import shapecreator.core.RunLoop;
import shapecreator.display.Frame;
import shapecreator.display.MainPanel;
import shapecreator.display.SidePanel;

import java.awt.Color;

public class ShapeCreatorLauncher {
    // Window settings
    public static final int SCREEN_HEIGHT = 800;

    // Main panel settings
    public static final int MAIN_PANEL_WIDTH = 800;
    public static final Color MAIN_PANEL_COLOR = Color.BLACK;

    // Side panel settings
    public static final int SIDE_PANEL_WIDTH = 200;
    public static final Color SIDE_PANEL_COLOR = Color.DARK_GRAY;

    public static void main(String[] args){
        Input input = new Input();

        MainPanel mainPanel = new MainPanel(input);
        SidePanel sidePanel = new SidePanel(input);

        Frame frame = new Frame();
        frame.add(mainPanel);
        frame.add(sidePanel);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        RunLoop runLoop = new RunLoop(mainPanel);
        runLoop.start();
    }
}
