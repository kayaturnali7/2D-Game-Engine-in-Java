package engine.scene;

import engine.entity.Entity;
import engine.event.EventBus;
import engine.event.GameEvent;
import engine.ui.UserInterface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class Scene {
    protected String name;
    protected int screenWidth;
    protected int screenHeight;
    protected Color bgColor;
    protected int fps;

    protected float drag;
    protected float gravity;


    private final ArrayList<Entity> entities = new ArrayList<>();
    private final ArrayList<UserInterface> userInterfaces = new ArrayList<>();

    private final ArrayList<Entity> pendingEntities = new ArrayList<>();
    private final ArrayList<UserInterface> pendingUserInterfaces = new ArrayList<>();

    public Scene(){
        SceneProperties settings = initialize();
        onStart();

        name = settings.name();
        screenWidth = settings.screenWidth();
        screenHeight = settings.screenHeight();
        bgColor = settings.bgColor();
        fps = settings.fps();

        if (!pendingEntities.isEmpty()){
            entities.addAll(pendingEntities);
            pendingEntities.clear();
        }
    }

    public void update(){
        onUpdate();
        updateLists();

        for (Entity entity : entities){
            entity.update();
        }

        for (UserInterface userInterface : userInterfaces){
            userInterface.update();
        }
    }

    protected abstract void onUpdate();

    protected abstract void onStart();

    protected abstract SceneProperties initialize();

    private void updateLists(){
        entities.removeIf(entity -> !entity.isActive());
        userInterfaces.removeIf(userInterface -> !userInterface.isActive());

        if (!pendingEntities.isEmpty()){
            entities.addAll(pendingEntities);
            pendingEntities.clear();
        }

        if (!pendingUserInterfaces.isEmpty()){
            userInterfaces.addAll(pendingUserInterfaces);
            pendingUserInterfaces.clear();
        }
    }

    public void instantiate(SceneComponent component){
        if (component instanceof UserInterface){
            pendingUserInterfaces.add((UserInterface) component);
        } else if (component instanceof Entity){
            pendingEntities.add((Entity) component);
        }
    }

    public ArrayList<Entity> getEntities(){return entities;}

    public ArrayList<UserInterface> getUserInterfaces(){return userInterfaces;}

    protected <T extends GameEvent> void subscribeToEvent(Class<T> eventClass, Consumer<T> action){
        EventBus.subscribe(eventClass, action);
    }

    public int getScreenWidth(){
        return screenWidth;
    }

    public int getScreenHeight(){
        return screenHeight;
    }

    public int getFps(){
        return fps;
    }

    public Color getBgColor(){
        return bgColor;
    }

    public String getName(){
        return name;
    }

    public float getDrag(){
        return drag;
    }

    public float getGravity(){
        return gravity;
    }
}