package asteroids.scenes;

import engine.scene.SceneConfig;
import engine.scene.Scene;
import asteroids.entities.*;
import asteroids.events.*;

public class GameScene extends Scene {

    @Override
    protected SceneConfig onStart() {
        subscribeToEvent(LaserFiredEvent.class, event -> onLaserFired(event.x(), event.y(), event.angle()));

       spawnEntity(new Player());
       spawnEntity(new TestEntity());

        return new SceneConfig("Game");
    }

    @Override
    protected void onUpdate() {
    }

    private void onLaserFired(float x, float y, double direction){
        spawnEntity(new Laser(x, y, direction));
    }
}
