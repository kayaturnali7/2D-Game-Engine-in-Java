package engine.core;

import engine.window.CorePanel;
import engine.window.CoreWindow;
import engine.scene.Scene;

import java.util.ArrayList;

public class Engine {
    public static void startEngine(ArrayList<Scene> scenes){
        Handler handler = new Handler(scenes);
        Input input = new Input();

        CoreWindow frame = new CoreWindow();
        CorePanel panel = new CorePanel(handler, input);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Loop runLoop = new Loop(panel, handler);
        runLoop.start();
    }
}
