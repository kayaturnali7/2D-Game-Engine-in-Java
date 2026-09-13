package engine;

import engine.core.CoreLoop;
import engine.core.CoreManager;
import engine.components.InputManager;
import engine.core.CoreWindow;
import engine.core.CorePanel;

public class Main {
    public static void main(String[] args){
        CoreManager coreManager = new CoreManager();
        InputManager input = new InputManager();

        CoreWindow frame = new CoreWindow();
        CorePanel panel = new CorePanel(coreManager, input);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        CoreLoop game = new CoreLoop(panel, coreManager);
        game.start();
    }
}