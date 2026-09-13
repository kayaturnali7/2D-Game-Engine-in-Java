package game;

import engine.core.Engine;
import engine.scene.Scene;
import game.scenes.GameScene;
import game.scenes.MenuScene;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ArrayList<Scene> scenes = new ArrayList<>();
        scenes.add(new GameScene());
        scenes.add(new MenuScene());

        Engine.startEngine(scenes);
    }
}
