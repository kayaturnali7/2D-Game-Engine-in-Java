package game.events;

public record LaserFiredEvent(float x, float y, double angle) implements GameEvent{}
