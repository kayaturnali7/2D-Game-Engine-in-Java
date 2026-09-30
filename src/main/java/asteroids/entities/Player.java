package asteroids.entities;

import engine.core.InputManager;

import asteroids.events.LaserFiredEvent;
import engine.entity.Entity;
import engine.model.Vector2D;
import engine.scene.Scene;
import engine.model.Shape;
import engine.model.Polygon;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Player extends Entity {
    private static final int ANGULAR_SPEED = 4;
    private static final double THRUST = 0.17;
    private static final double COOLDOWN_TIME = 0.25;
    private static final Color COLOR = Color.WHITE;

    private double cooldownTick = 0;
    private boolean firing = false;
    private boolean canFire = false;

    public Player(Scene scene) {
        super(scene,10, true);
    }

    @Override
    protected void onUpdate() {
        firing = InputManager.isKeyPressed(KeyEvent.VK_SPACE);

        if (InputManager.isKeyPressed(KeyEvent.VK_W)){
            applyThrust();
        }

        if (InputManager.isKeyPressed(KeyEvent.VK_A)){
            rotate(-ANGULAR_SPEED);
        } else if (InputManager.isKeyPressed(KeyEvent.VK_D)) {
            rotate(ANGULAR_SPEED);
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

        Vector2D v1 = new Vector2D(0, -dTop);
        Vector2D v2 = new Vector2D(-(base /2), dBase);
        Vector2D v3 = new Vector2D(base/2, dBase);

        Vector2D[] vertices = new Vector2D[]{v1, v2, v3};

        return new Polygon(vertices, COLOR);
    }

    @Override
    protected void onDraw(Graphics2D g2d) {

    }

    private void applyThrust(){
        applyForce(THRUST, getDirection());
    }

    private void updateCooldown(){
        if (cooldownTick > 0){
            cooldownTick -= 1;
        }

        canFire = firing && cooldownTick == 0;
    }

    private void fireLaser(){
        Vector2D vertex = getVertex(0);

        double x = vertex.getX();
        double y = vertex.getY();
        double direction = getDirection();

        addEvent(new LaserFiredEvent(x,y,direction));
        cooldownTick = COOLDOWN_TIME * scene.getFps();
    }
}
