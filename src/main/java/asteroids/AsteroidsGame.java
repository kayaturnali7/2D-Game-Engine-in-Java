package asteroids;

import engine.core.Engine;
import engine.scene.Scene;
import asteroids.scenes.GameScene;
import asteroids.scenes.MenuScene;

public class AsteroidsGame {
    public static void main(String[] args){
        Scene[] scenes = {new GameScene(), new MenuScene()};
        // change scenes to arrays, not arraylist, also check engine and game code to see if you can cut down on new objects called and arraylist
        new Engine(scenes);
    }
}
