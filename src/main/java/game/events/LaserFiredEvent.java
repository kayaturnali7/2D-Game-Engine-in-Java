package game.events;

import engine.data.GameEvent;

public record LaserFiredEvent(float x, float y, double angle) implements GameEvent {}
