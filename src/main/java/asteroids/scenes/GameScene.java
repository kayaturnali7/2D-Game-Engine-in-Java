package asteroids.scenes;

import engine.actors.Entity;
import engine.scene.Scene;

// Let these be wildcard imports
import asteroids.entities.*;
import engine.util.MathUtil;

import java.awt.Color;

public class GameScene extends Scene {

    private final double drag = 0.97;

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
        applyDrag();
    }


    private void onLaserFired(double x, double y, double direction){
        laserCount += 1;
        instantiate(new Laser(this, x, y, direction, laserCount));
    }

    private void onCollision(Entity e1, Entity e2){

    }

    private void applyDrag() {
        for (Entity e : entities){
           e.vx = e.vx * drag;
           e.vy = e.vy * drag;

           if (MathUtil.pythagorean(e.vx, e.vy) < 0.01){
               e.vx = 0;
               e.vy = 0;
           }
        }
    }
}
