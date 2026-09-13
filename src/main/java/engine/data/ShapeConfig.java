package engine.data;

public record ShapeConfig(
    int[] xPoints,
    int[] yPoints,
    boolean drawBounds,
    boolean fillShape
) {}
