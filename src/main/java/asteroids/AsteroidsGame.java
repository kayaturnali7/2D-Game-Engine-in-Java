package asteroids;

import engine.core.Engine;
import engine.scene.Scene;
import asteroids.scenes.GameScene;
import asteroids.scenes.MenuScene;

import java.util.ArrayList;

public class AsteroidsGame {
    public static void main(String[] args){
        ArrayList<Scene> scenes = new ArrayList<>();
        scenes.add(new GameScene());
        scenes.add(new MenuScene());

        new Engine(scenes);
    }
}
