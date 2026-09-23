package asteroids.scenes;

import engine.entity.Entity;
import engine.physics.CollisionEvent;
import engine.scene.SceneProperties;
import engine.scene.Scene;

// Let these be wildcard imports
import asteroids.entities.*;
import asteroids.events.*;

import java.awt.Color;

public class GameScene extends Scene {

    private Entity player;
    private Entity testEntity;

    @Override
    protected SceneProperties initialize() {
        name = "Asteroids Remake";
        screenWidth = 800;
        screenHeight = 800;
        bgColor = Color.BLACK;
        fps = 60;
        drag =  0.98F;

        return new SceneProperties(name, screenWidth, screenHeight, bgColor, fps);
    }

    @Override
    protected void onStart() {
        subscribeToEvent(CollisionEvent.class, event -> onCollision(event.e1(), event.e2()));
        subscribeToEvent(LaserFiredEvent.class, event -> onLaserFired(event.x(), event.y(), event.angle()));

        player = new Player(this);
        testEntity = new TestEntity(this);

        instantiate(player);
        instantiate(testEntity);
    }

    @Override
    protected void onUpdate() {

    }


    private void onLaserFired(float x, float y, double direction){
        instantiate(new Laser(this, x, y, direction));
    }

    private void onCollision(Entity e1, Entity e2){

    }

}
