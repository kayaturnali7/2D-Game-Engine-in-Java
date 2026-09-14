package asteroids.scenes;

import engine.data.SceneConfig;
import engine.entity.Entity;
import engine.scene.Scene;
import asteroids.entities.*;
import asteroids.events.*;

import java.util.ArrayList;

public class GameScene extends Scene {

    private final Player player = new Player();
    private final TestEntity testEntity = new TestEntity();
    private final ArrayList<Entity> entities = new ArrayList<>();

    @Override
    protected SceneConfig onStart() {
        subscribeToEvent(LaserFiredEvent.class, event -> onLaserFired(event.x(), event.y(), event.angle()));

        entities.add(player);
        entities.add(testEntity);

        return new SceneConfig("Game", entities);
    }

    @Override
    protected void onUpdate() {
    }

    private void onLaserFired(float x, float y, double direction){
        spawnEntity(new Laser(x, y, direction));
    }
}
