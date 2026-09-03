package shapecreator.display;
import shapecreator.core.Input;
import shapecreator.ShapeCreatorLauncher;
import java.awt.FlowLayout;

public class SidePanel extends Panel {
    public SidePanel(Input input) {
        super(input, ShapeCreatorLauncher.SIDE_PANEL_WIDTH, ShapeCreatorLauncher.SCREEN_HEIGHT, ShapeCreatorLauncher.SIDE_PANEL_COLOR);
        this.setLocation(ShapeCreatorLauncher.MAIN_PANEL_WIDTH,0);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    }
}
