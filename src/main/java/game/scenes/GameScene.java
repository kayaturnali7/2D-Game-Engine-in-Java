package game.scenes;

import engine.event.EventBus;
import engine.scene.Scene;
import game.entities.Laser;
import game.entities.Player;
import game.entities.TestEntity;
import game.events.LaserFiredEvent;


public class GameScene extends Scene {

    @Override
    protected void onUpdate() {
    }

    @Override
    protected void onStart() {
        EventBus.subscribe(LaserFiredEvent.class, event -> onLaserFired(event.x(), event.y(), event.angle()));

        spawnEntity(new Player());
        spawnEntity(new TestEntity());
    }

    private void onLaserFired(float x, float y, double direction){
        spawnEntity(new Laser(x, y, direction));
    }
}
