package engine;

import engine.core.CoreLoop;
import engine.core.LoopManager;
import engine.components.InputManager;
import engine.core.CoreFrame;
import engine.core.CorePanel;

public class Main {
    public static void main(String[] args){
        LoopManager loopManager = new LoopManager();
        InputManager input = new InputManager();

        CoreFrame frame = new CoreFrame();
        CorePanel panel = new CorePanel(loopManager, input);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        CoreLoop game = new CoreLoop(panel, loopManager);
        game.start();
    }
}