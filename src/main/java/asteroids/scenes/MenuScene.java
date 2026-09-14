package asteroids.scenes;

import engine.scene.Scene;
import asteroids.entities.*;

public class MenuScene extends Scene {
    @Override
    protected void onUpdate() {

    }

    @Override
    protected void onStart() {
        spawnEntity(new TestEntity());
    }
}
