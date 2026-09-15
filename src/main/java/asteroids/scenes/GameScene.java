package asteroids.scenes;

import engine.scene.SceneConfig;
import engine.scene.Scene;
import asteroids.entities.*;
import asteroids.events.*;

public class GameScene extends Scene {

    @Override
    protected SceneConfig onStart() {
        subscribeToEvent(LaserFiredEvent.class, event -> onLaserFired(event.x(), event.y(), event.angle()));

       instantiateEntity(new Player());
       instantiateEntity(new TestEntity());

        return new SceneConfig("Game");
    }

    @Override
    protected void onUpdate() {
    }

    private void onLaserFired(float x, float y, double direction){
        instantiateEntity(new Laser(x, y, direction));
    }
}
