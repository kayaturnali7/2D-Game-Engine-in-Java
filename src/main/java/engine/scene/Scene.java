package engine.scene;

import engine.entity.Entity;
import engine.event.EventBus;
import engine.event.GameEvent;
import engine.ui.UserInterface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class Scene {
    private String name;
    private int screenWidth;
    private int screenHeight;
    private Color bgColor;
    private int fps;

    private final ArrayList<Entity> entities = new ArrayList<>();
    private final ArrayList<UserInterface> userInterfaces = new ArrayList<>();

    private final ArrayList<Entity> pendingEntities = new ArrayList<>();
    private final ArrayList<UserInterface> pendingUserInterfaces = new ArrayList<>();

    public Scene(){
        SceneSettings settings = onStart();

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

        for (Entity entity : entities){
            entity.update();
        }

        for (UserInterface userInterface : userInterfaces){
            userInterface.update();
        }

        updateEntityList();
        updateUIList();
    }

    protected abstract void onUpdate();

    protected abstract SceneSettings onStart();

    private void updateEntityList(){
        entities.removeIf(entity -> !entity.isActive());

        if (!pendingEntities.isEmpty()){
            entities.addAll(pendingEntities);
            pendingEntities.clear();
        }
    }

    private void updateUIList(){
        userInterfaces.removeIf(userInterface -> !userInterface.isActive());

        if (!pendingUserInterfaces.isEmpty()){
            userInterfaces.addAll(pendingUserInterfaces);
            pendingUserInterfaces.clear();
        }
    }


    public void instantiate(SceneInterface component){
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
}
