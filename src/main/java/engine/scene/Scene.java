package engine.scene;

import engine.entity.Entity;
import engine.event.EventBus;
import engine.event.GameEvent;
import engine.model.Vector2;
import engine.ui.UserInterface;

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

    private final ArrayList<Entity> entities = new ArrayList<>();
    private final ArrayList<UserInterface> userInterfaces = new ArrayList<>();
    private final ArrayList<SceneComponent> pending = new ArrayList<>();

    public Scene(){
        initialize();
        onStart();
        updateLists();
    }

    private void updateLists(){
        if (!pending.isEmpty()){
            for (SceneComponent component : pending){
                if (component instanceof Entity){
                    entities.add((Entity) component);
                } else if (component instanceof UserInterface){
                    userInterfaces.add((UserInterface) component);
                }
            }
            pending.clear();
        }

        entities.removeIf(entity -> !entity.isActive());
        userInterfaces.removeIf(ui -> !ui.isActive());
    }

    public void update(){
        onUpdate();

        entities.forEach(Entity::update);
        userInterfaces.forEach(UserInterface::update);

        updateLists();
    }

    protected abstract void onUpdate();
    protected abstract void onStart();
    protected abstract void initialize();

    public void instantiate(SceneComponent component){
        pending.add(component);
    }

    public ArrayList<Entity> getEntities(){return entities;}

    public ArrayList<UserInterface> getUserInterfaces(){return userInterfaces;}

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