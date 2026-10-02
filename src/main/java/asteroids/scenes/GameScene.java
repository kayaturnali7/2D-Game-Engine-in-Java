package asteroids.scenes;

import engine.actors.Entity;
import engine.scene.Scene;

// Let these be wildcard imports
import asteroids.entities.*;
import asteroids.events.*;

import java.awt.Color;

public class GameScene extends Scene {

    private Entity player;

    private int laserCount = 0;

    @Override
    protected void initialize() {
        setTitle("Asteroid Remake");
        setDimension(900,600);
        setBgColor(Color.BLACK);
        setFps(60);
        setDrag(0.97);
    }

    @Override
    protected void onStart() {
        subscribeToEvent(LaserFiredEvent.class, event -> onLaserFired(event.x(), event.y(), event.angle()));

        player = new Player(this);
        player.moveTo(getCenter());

        instantiate(player);
    }


    @Override
    protected void onUpdate() {
    }


    private void onLaserFired(double x, double y, double direction){
        laserCount += 1;
        instantiate(new Laser(this, x, y, direction, laserCount));
    }

    private void onCollision(Entity e1, Entity e2){

    }

}
