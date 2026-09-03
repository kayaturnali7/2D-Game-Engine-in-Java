package game.events;

import game.entitycomponents.Entity;

public record CollisionEvent (Entity entity, Entity collider) implements GameEvent {}
