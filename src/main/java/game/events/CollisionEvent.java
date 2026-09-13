package game.events;

import engine.ecs.Entity;
import engine.data.GameEvent;

public record CollisionEvent (Entity entity, Entity collider) implements GameEvent {}
