package engine.data;

import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public record Geometry(Shape shape, Area area, Rectangle2D bounds) {
}
