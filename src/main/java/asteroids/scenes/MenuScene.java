package asteroids.scenes;

import engine.scene.SceneConfig;
import engine.scene.Scene;

public class MenuScene extends Scene {
    @Override
    protected void onUpdate() {

    }

    @Override
    protected SceneConfig onStart() {

        return new SceneConfig("Menu");
    }
}
