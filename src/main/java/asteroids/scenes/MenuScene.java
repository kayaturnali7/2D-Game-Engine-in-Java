package asteroids.scenes;

import engine.scene.SceneProperties;
import engine.scene.Scene;

import java.awt.Color;

public class MenuScene extends Scene {
    @Override
    protected void onUpdate() {

    }

    @Override
    protected void onStart() {


    }

    @Override
    protected SceneProperties initialize() {
        name = "Asteroids Remake";
        screenWidth = 800;
        screenHeight = 800;
        bgColor = Color.BLACK;
        fps = 60;

        return new SceneProperties(name, screenWidth, screenHeight, bgColor, fps);
    }
}
