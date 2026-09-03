package game.entities;

import game.data.ShapeConfig;
import game.engine.EventBus;
import game.core.GameLoop;
import game.engine.InputManager;

import game.entitycomponents.*;
import game.events.LaserFiredEvent;
import game.utils.GeneralUtil;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

public class Player extends Entity {
    private static final int ANGULAR_SPEED = 4;
    private static final float THRUST = 0.17F;
    private static final float COOLDOWN_TIME = 0.25F;

    private float cooldownTick = 0;
    private boolean firing = false;
    private boolean canFire = false;


    public Player() {
        super(10, Color.white , true);
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
    protected ShapeConfig shapeConfig() {
        int side = 60;
        int base = (int) (side * (7.0 / 9.0));
        double height = Math.sqrt((Math.pow(side, 2)) - Math.pow(((double)base / 2), 2));

        double dTop = height * ((double) 2 / 3);
        double dBase = height * ((double) 1 / 3);

        int v1x = 0;
        int v1y = (int) (-dTop);

        int v2x = -(base / 2);
        int v2y = (int) (dBase);

        int v3x = (base / 2);
        int v3y = (int) (dBase);

        // TOP, BOTTOM-LEFT, BOTTOM-RIGHT
        int[] xPoints = new int[]{v1x, v2x, v3x};
        int[] yPoints = new int[]{v1y, v2y, v3y};

        return new ShapeConfig(xPoints, yPoints, false, false);
    }

    @Override
    protected void onDraw(Graphics2D g2d) {

    }

    private void applyThrust(){
        applyForce(THRUST);
    }

    private void updateCooldown(){
        if (cooldownTick > 0){
            cooldownTick -= 1;
        }

        canFire = firing && cooldownTick == 0;
    }

    private void fireLaser(){
        Shape shape = getGeometry().shape();
        Point2D.Float point = GeneralUtil.getVertex(shape, 0);

        float x = point.x;
        float y = point.y;
        double direction = getDirection().look();

        EventBus.add(new LaserFiredEvent(x,y,direction));
        cooldownTick = COOLDOWN_TIME * GameLoop.FPS;
    }
}
