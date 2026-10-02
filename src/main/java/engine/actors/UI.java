package engine.actors;

import engine.render.RenderComponent;
import engine.scene.Scene;

public abstract class UI extends Actor implements RenderComponent {

    public UI(Scene scene, String name){super(scene, name);}

}
