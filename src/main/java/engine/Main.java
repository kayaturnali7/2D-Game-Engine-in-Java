package engine;

import engine.core.CoreLoop;
import engine.core.CoreHandler;
import engine.components.InputManager;
import engine.core.CoreWindow;
import engine.core.CorePanel;

public class Main {
    public static void main(String[] args){
        CoreHandler coreHandler = new CoreHandler();
        InputManager input = new InputManager();

        CoreWindow frame = new CoreWindow();
        CorePanel panel = new CorePanel(coreHandler, input);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        CoreLoop game = new CoreLoop(panel, coreHandler);
        game.start();
    }
}