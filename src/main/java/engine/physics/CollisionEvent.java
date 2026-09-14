package engine.physics;

import engine.entity.Entity;
import engine.event.GameEvent;

public record CollisionEvent (Entity entity, Entity collider) implements GameEvent {}
