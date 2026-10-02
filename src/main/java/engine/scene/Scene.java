package engine.scene;

import engine.actors.Entity;
import engine.actors.Actor;
import engine.event.EventBus;
import engine.event.GameEvent;
import engine.data.Vector2;
import engine.actors.UI;
import engine.physics.Collider;
import engine.physics.PhysicsBody;
import engine.render.RenderComponent;

import java.awt.Color;
import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class Scene {
    private String title;

    private int width, height;
    private Vector2 center;
    private Vector2 north, east, south, west;
    private Vector2 topLeft, topRight, bottomLeft, bottomRight;

    private Color bgColor;
    private int fps;

    private double drag;
    private double gravity;

    private final ArrayList<Actor> actors = new ArrayList<>();
    private final ArrayList<Actor> pendingActors = new ArrayList<>();

    private final ArrayList<Collider> colliders = new ArrayList<>();
    private final ArrayList<Collider> pendingColliders = new ArrayList<>();

    private final ArrayList<RenderComponent> renderComponents = new ArrayList<>();
    private final ArrayList<RenderComponent> pendingRenderComponents = new ArrayList<>();

    public Scene(){
        initialize();
        onStart();
        updateLists();
    }

    private void updateLists(){
        if(!pendingActors.isEmpty()){
            actors.addAll(pendingActors);
            pendingActors.clear();
        }

        if (!pendingColliders.isEmpty()){
            colliders.addAll(pendingColliders);
            pendingColliders.clear();
        }

        if(!pendingRenderComponents.isEmpty()){
            renderComponents.addAll(pendingRenderComponents);
            pendingRenderComponents.clear();
        }

        actors.removeIf(actor -> !actor.isActive());
        colliders.removeIf(collider -> !actors.contains(collider));
        renderComponents.removeIf(renderComponent -> !actors.contains(renderComponent));


    }

    public void update(){
        onUpdate();
        for (Actor actor : actors){
            actor.update();
        }
        updateLists();
    }

    protected abstract void onUpdate();
    protected abstract void onStart();
    protected abstract void initialize();

    public void instantiate(Actor actor){
        pendingActors.add(actor);

        if (actor instanceof Collider){
            pendingColliders.add((Collider) actor);
        }

        if (actor instanceof RenderComponent) {
            pendingRenderComponents.add((RenderComponent) actor);
        }

        System.out.println("\"" + actor.getName() + "\"" + " instantiated in scene: " + this);
    }

    public ArrayList<Actor> getActors(){return actors;}

    public ArrayList<RenderComponent> getRenderComponents(){return renderComponents;}

    public ArrayList<Collider> getColliders(){return colliders;}


    protected <T extends GameEvent> void subscribeToEvent(Class<T> eventClass, Consumer<T> action){
        EventBus.subscribe(eventClass, action);
    }

    public void setDimension(int width, int height){
        this.width = width;
        this.height = height;

        this.center = new Vector2((double) width /2, (double) height /2);
        this.north = new Vector2((double) width /2, 0);
        this.east = new Vector2(width, (double) height /2);
        this.south = new Vector2((double) width /2, height);
        this.west = new Vector2(0, (double) height /2);

        this.topLeft = new Vector2(0,0);
        this.topRight = new Vector2(width,0);
        this.bottomLeft = new Vector2(0,height);
        this.bottomRight = new Vector2(width,height);
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setFps(int fps){
        this.fps = fps;
    }

    public int getFps(){
        return fps;
    }

    public void setDrag(double drag){
        this.drag = drag;
    }

    public double getDrag(){
        return drag;
    }

    public void setBgColor(Color color){
        this.bgColor = color;
    }

    public Vector2 getCenter(){
        return center;
    }

    public Vector2 getNorth(){
        return north;
    }

    public Vector2 getEast() {return east;}

    public Vector2 getSouth() {return south;}

    public Vector2 getWest() {return west;}

    public Vector2 getTopLeft() {return topLeft;}

    public Vector2 getTopRight() {return topRight;}

    public Vector2 getBottomLeft() {return bottomLeft;}

    public Vector2 getBottomRight() {return bottomRight;}

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public String getTitle(){
        return title;
    }
}