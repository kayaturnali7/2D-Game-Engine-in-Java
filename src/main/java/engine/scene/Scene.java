package engine.scene;

import engine.entity.Entity;
import engine.event.EventBus;
import engine.event.GameEvent;

import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class Scene {

    private final String sceneName;
    private final ArrayList<Entity> entities = new ArrayList<>();
    private final ArrayList<Entity> pending = new ArrayList<>();

    public Scene(){
        SceneConfig sceneConfig = onStart();
        sceneName = sceneConfig.name();

        if (!pending.isEmpty()){
            entities.addAll(pending);
            pending.clear();
        }
    }

    public void update(){
        onUpdate();
        updateEntities();
    }

    protected abstract void onUpdate();

    protected abstract SceneConfig onStart();

    private void updateEntities(){
        // update each entity's logic
        for (Entity entity : entities){
            entity.update();
        }

        cleanup();

        // add any pending entities
        if (!pending.isEmpty()){
            entities.addAll(pending);
            pending.clear();
        }
    }


    protected void instantiateEntity(Entity entity){
        pending.add(entity);
    }

    private void cleanup(){
        // clear dead entities
        entities.removeIf(entity -> !entity.isAlive());
    }

    public ArrayList<Entity> getEntities(){
        return entities;
    }

    protected <T extends GameEvent> void subscribeToEvent(Class<T> eventClass, Consumer<T> action){
        EventBus.subscribe(eventClass, action);
    }

}
