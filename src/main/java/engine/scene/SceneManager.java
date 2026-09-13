package engine.scene;

import java.util.ArrayList;

public class SceneManager {

    private final ArrayList<Scene> scenes = new ArrayList<>();
    private Scene currentScene;

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void addScenes(ArrayList<Scene> scenesList){
        scenes.addAll(scenesList);
        currentScene = scenes.get(0);
    }
}
