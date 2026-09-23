package asteroids.scenes;

import engine.scene.SceneSettings;
import engine.scene.Scene;

// Let these be wildcard imports
import asteroids.entities.*;
import asteroids.events.*;

import java.awt.Color;

public class GameScene extends Scene {

    @Override
    protected SceneSettings onStart() {
        subscribeToEvent(LaserFiredEvent.class, event -> onLaserFired(event.x(), event.y(), event.angle()));

        instantiate(new Player(this));
        instantiate(new TestEntity(this));

        String name = "Asteroids Remake";
        int screenWidth = 1200;
        int screenHeight = 600;
        Color bgColor = Color.BLACK;
        int fps = 60;

        return new SceneSettings(name, screenWidth, screenHeight, bgColor, fps);
    }

    @Override
    protected void onUpdate() {
    }

    private void onLaserFired(float x, float y, double direction){
        instantiate(new Laser(this, x, y, direction));
    }
}
