package engine.actors;

import engine.scene.Scene;

public abstract class Actor {

    protected final Scene scene;

    protected String name;
    private boolean active = true;

    public Actor(Scene scene, String name){
        this.scene = scene;
        this.name = name;

    }

    public void update(){
        onUpdate();
    }

    protected abstract void onUpdate();

    public boolean isActive(){
        return active;
    }

    public void destroy(){
        active = false;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Scene getScene(){return scene;}
}


