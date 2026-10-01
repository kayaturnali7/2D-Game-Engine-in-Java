package engine.ui;

import engine.scene.Scene;
import engine.scene.SceneComponent;

import java.awt.Graphics2D;

public abstract class UserInterface extends SceneComponent {

    public UserInterface(Scene scene, String name){
        super(scene, name);

    }

    public final void update(){
        onUpdate();
    }

    public final void draw(Graphics2D g2d){
        onDraw(g2d);

    }

    protected abstract void onUpdate();
    protected abstract void onDraw(Graphics2D g2d);
}
