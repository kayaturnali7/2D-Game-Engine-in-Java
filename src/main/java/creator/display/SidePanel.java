package creator.display;
import creator.core.Input;
import creator.Main;
import java.awt.FlowLayout;

public class SidePanel extends Panel {
    public SidePanel(Input input) {
        super(input, Main.SIDE_PANEL_WIDTH, Main.SCREEN_HEIGHT, Main.SIDE_PANEL_COLOR);
        this.setLocation(Main.MAIN_PANEL_WIDTH,0);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    }
}
