package engine.physics;

import engine.ecs.Entity;
import engine.event.GameEvent;

public record CollisionEvent (Entity entity, Entity collider) implements GameEvent {}
