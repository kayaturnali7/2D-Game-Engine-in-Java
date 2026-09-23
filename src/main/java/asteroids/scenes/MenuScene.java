package asteroids.scenes;

import engine.scene.SceneSettings;
import engine.scene.Scene;

import java.awt.Color;

public class MenuScene extends Scene {
    @Override
    protected void onUpdate() {

    }

    @Override
    protected SceneSettings onStart() {

        String name = "Asteroids Remake";
        int screenWidth = 800;
        int screenHeight = 800;
        Color bgColor = Color.BLACK;
        int fps = 60;

        return new SceneSettings(name, screenWidth, screenHeight, bgColor, fps);
    }
}
