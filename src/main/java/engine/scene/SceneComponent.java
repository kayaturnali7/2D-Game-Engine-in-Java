package engine.scene;

import java.awt.Graphics2D;

public interface SceneComponent {

    void update();

    void draw(Graphics2D g2d);

}
