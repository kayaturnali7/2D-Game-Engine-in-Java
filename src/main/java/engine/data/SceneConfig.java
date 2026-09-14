package engine.data;

import engine.entity.Entity;

import java.util.ArrayList;

public record SceneConfig(
    String name,
    ArrayList<Entity> entities
){}
