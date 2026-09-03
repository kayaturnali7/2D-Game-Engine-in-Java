package game.display;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame(){
        this.setTitle("Asteroids Remake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }
}
