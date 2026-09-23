package engine.core;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class Window extends JFrame {

    public Window(JPanel panel, SystemHandler systemHandler){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        String sceneName = systemHandler.getCurrentScene().getName();
        this.setTitle(sceneName);

        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
