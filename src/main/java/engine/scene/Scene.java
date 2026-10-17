package engine.scene;

import engine.actors.Actor;
import engine.data.Vector2;
import engine.scene.ArrayTypes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Scene {

    private final String title;
    public final int width, height;

    private Color bgColor;
    private int fps;

    protected final HashMap<ArrayTypes, ArrayList<T>> active = new HashMap<>();
    protected final HashMap<ArrayTypes, ArrayList<T>> pending = new HashMap<>();

    public static <K,V> void addValue(HashMap<K, ArrayList<V>> map, K key, V value) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }


    public Scene(){
        
        addValue(active, ArrayTypes.ACTOR, Actor.class);
        System.out.println(active);

        initialize();
        onStart();
        updateLists();
    }

    private void updateLists(){
        if(!pendingActors.isEmpty()){
            actors.addAll(pendingActors);
            pendingActors.clear();
        }

        if(!pendingEntities.isEmpty()){
            entities.addAll(pendingEntities);
            pendingActors.clear();
        }


        entities.removeIf(entity -> !entity.isActive());
        actors.removeIf(actor -> !actor.isActive());
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
        if (actor instanceof Entity){
            pendingEntities.add((Entity) actor);
        }
    }

    public ArrayList<Actor> getActors(){return actors;}

    public ArrayList<Entity> getEntities(){return entities;}

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

    public void setBgColor(Color color){
        this.bgColor = color;
    }
}