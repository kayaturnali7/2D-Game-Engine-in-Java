package creator.display;

import javax.swing.JFrame;
import java.awt.FlowLayout;

public class Frame extends JFrame{
    public Frame(){
        this.setTitle("Shape Creator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 0, 0);
        this.setLayout(layout);
    }
}
