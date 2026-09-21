package engine.ui;

import java.awt.Graphics2D;

public abstract class UserInterface {

    private boolean active = true;

    public final void update(){
        onUpdate();
    }

    public final void draw(Graphics2D g2d){
        onDraw(g2d);
    }

    protected abstract void onUpdate();
    protected abstract void onDraw(Graphics2D g2d);

    public void destroy(){
        active = false;
    }

    public boolean isActive(){
        return active;
    }
}
