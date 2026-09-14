package asteroids.events;

import engine.event.GameEvent;

public record LaserFiredEvent(float x, float y, double angle) implements GameEvent {}
