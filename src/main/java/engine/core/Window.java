package engine.core;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class Window extends JFrame {

    public Window(JPanel panel, Manager manager){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String sceneName = manager.getCurrentScene().getName();
        this.setTitle(sceneName);

        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    protected void update(String sceneName){
        this.pack();
        this.setTitle(sceneName);
    }

}
