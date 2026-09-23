package engine.physics;

import engine.entity.Entity;
import engine.event.GameEvent;

public record CollisionEvent (Entity e1, Entity e2) implements GameEvent {}
