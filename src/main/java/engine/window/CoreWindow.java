package engine.window;

import javax.swing.JFrame;

public class CoreWindow extends JFrame {
    public CoreWindow(){
        this.setTitle("Asteroids Remake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }
}
