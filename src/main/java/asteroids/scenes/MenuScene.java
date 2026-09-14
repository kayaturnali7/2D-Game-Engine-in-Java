package asteroids.scenes;

import engine.data.SceneConfig;
import engine.entity.Entity;
import engine.scene.Scene;
import asteroids.entities.*;

import java.util.ArrayList;

public class MenuScene extends Scene {
    @Override
    protected void onUpdate() {

    }

    @Override
    protected SceneConfig onStart() {
        ArrayList<Entity> startEntities = new ArrayList<>();
        startEntities.add(new TestEntity());

        return new SceneConfig("Menu", startEntities);
    }
}
