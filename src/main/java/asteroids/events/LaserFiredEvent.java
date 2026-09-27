package asteroids.events;

import engine.event.GameEvent;

public record LaserFiredEvent(double x, double y, double angle) implements GameEvent {}
