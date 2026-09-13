package game.scenes;

import engine.scene.Scene;
import game.entities.TestEntity;

public class MenuScene extends Scene {
    @Override
    protected void onUpdate() {

    }

    @Override
    protected void onStart() {
        spawnEntity(new TestEntity());
    }
}
