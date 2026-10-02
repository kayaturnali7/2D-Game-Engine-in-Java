package asteroids.entities;

import engine.actors.Actor;
import engine.core.InputSystem;

import engine.actors.Entity;
import engine.data.Vector2;
import engine.physics.Collider;
import engine.physics.PhysicsBody;
import engine.scene.Scene;
import engine.geometry.Shape;
import engine.geometry.ConvexPolygon;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Player extends Entity implements Collider {
    private static final int ANGULAR_SPEED = 4;
    private static final double THRUST = 0.17;
    private static final double COOLDOWN_TIME = 0.25;
    private static final Color COLOR = Color.WHITE;

    private double cooldownTick = 0;
    private boolean firing = false;
    private boolean canFire = false;

    public Player(Scene scene) {
        super(scene,"Player");
    }

    @Override
    protected void onUpdate() {
        firing = InputSystem.isKeyPressed(KeyEvent.VK_SPACE);

        if (InputSystem.isKeyPressed(KeyEvent.VK_W)) {
            applyThrust();
        }

        if (InputSystem.isKeyPressed(KeyEvent.VK_A)) {
            rotate(ANGULAR_SPEED);
        } else if (InputSystem.isKeyPressed(KeyEvent.VK_D)) {
            rotate(-ANGULAR_SPEED);
        }

        if (canFire){
            fireLaser();
        }
        updateCooldown();
    }

    @Override
    protected Shape shapeInit() {
        double side = 60;
        double base = side * (7.0 / 9.0);
        double height = Math.sqrt((Math.pow(side, 2)) - Math.pow((base / 2), 2));

        double dTop = height * ((double) 2 / 3);
        double dBase = height * ((double) 1 / 3);

        Vector2 v1 = new Vector2(0, -dTop);
        Vector2 v2 = new Vector2(-(base /2), dBase);
        Vector2 v3 = new Vector2(base/2, dBase);

        Vector2[] vertices = new Vector2[]{v1, v2, v3};

        return new ConvexPolygon(vertices, COLOR);
    }


    private void applyThrust(){
        applyForce(THRUST,getDirection());
    }

    private void updateCooldown(){
        if (cooldownTick > 0){
            cooldownTick -= 1;
        }

        canFire = firing && cooldownTick == 0;
    }

    private void fireLaser(){
        cooldownTick = COOLDOWN_TIME * scene.getFps();
        System.out.println("Firing laser");
    }

    @Override
    public void onCollision(Actor actor) {

    }
}
