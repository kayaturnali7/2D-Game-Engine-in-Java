package engine.scene;


public abstract class SceneComponent{

    protected Scene scene;
    private boolean active = true;

    protected String name;

    public SceneComponent(Scene scene, String name){
        this.scene = scene;
        this.name = name;
    }

    public boolean isActive(){
        return active;
    }

    public void destroy(){
        active = false;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Scene getScene(){return scene;}
}


