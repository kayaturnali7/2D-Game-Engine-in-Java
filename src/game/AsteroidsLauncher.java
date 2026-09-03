package game;

import game.core.GameLoop;
import game.core.LoopManager;
import game.engine.InputManager;
import game.display.GameFrame;
import game.display.GamePanel;

public class AsteroidsLauncher {
    static void main(){
        System.out.println("test from school computer");
        LoopManager loopManager = new LoopManager();
        InputManager input = new InputManager();

        GameFrame frame = new GameFrame();
        GamePanel panel = new GamePanel(loopManager, input);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        GameLoop game = new GameLoop(panel, loopManager);
        game.start();
    }
}