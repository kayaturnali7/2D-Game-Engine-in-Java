package engine.scene;

import engine.entity.Entity;

import java.util.ArrayList;

public abstract class Scene {

    private final ArrayList<Entity> entities = new ArrayList<>();
    private final ArrayList<Entity> pending = new ArrayList<>();

    public Scene(){
        onStart();
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
    protected abstract void onStart();

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


    protected void spawnEntity(Entity entity){
        pending.add(entity);
    }

    private void cleanup(){
        // clear dead entities
        entities.removeIf(entity -> !entity.isAlive());
    }

    public ArrayList<Entity> getEntities(){
        return entities;
    }

}
