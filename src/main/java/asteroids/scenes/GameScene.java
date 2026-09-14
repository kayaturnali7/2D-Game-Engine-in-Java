package asteroids.scenes;

import engine.data.SceneConfig;
import engine.entity.Entity;
import engine.scene.Scene;
import asteroids.entities.*;
import asteroids.events.*;

import java.util.ArrayList;

public class GameScene extends Scene {
    @Override
    protected SceneConfig onStart() {
        subscribeEvent(LaserFiredEvent.class, event -> onLaserFired(event.x(), event.y(), event.angle()));

        ArrayList<Entity> startEntities = new ArrayList<>();
        startEntities.add(new Player());
        startEntities.add(new TestEntity());

        return new SceneConfig("Game", startEntities);
    }

    @Override
    protected void onUpdate() {


    }

    private void onLaserFired(float x, float y, double direction){
        spawnEntity(new Laser(x, y, direction));
    }
}
