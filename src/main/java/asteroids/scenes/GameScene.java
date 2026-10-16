package asteroids.scenes;

import engine.actors.Entity;
import engine.scene.Scene;

// Let these be wildcard imports
import asteroids.entities.*;

import java.awt.Color;

public class GameScene extends Scene {

    private final double DRAG = 0.97;

    private Entity player;
    private Entity testEntity;

    private int laserCount = 0;

    @Override
    protected void initialize() {
        setTitle("Asteroid Remake");
        setDimension(900,600);
        setBgColor(Color.BLACK);
        setFps(60);
    }

    @Override
    protected void onStart() {

        player = new Player(this);
        player.moveTo(getCenter().getX(), getCenter().getY());

        testEntity = new TestEntity(this, "t");
        testEntity.moveTo(200,200);

        instantiate(player);
        instantiate(testEntity);
    }


    @Override
    protected void onUpdate() {
        player.vx *= DRAG;
        player.vy *= DRAG;

        
    }


    private void onCollision(Entity e1, Entity e2){

    }
}
