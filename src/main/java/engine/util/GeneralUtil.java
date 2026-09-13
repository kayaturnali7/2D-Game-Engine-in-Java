package engine.util;

import java.awt.Shape;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;

public class GeneralUtil {
    public static Point2D.Float getVertex(Shape shape, int targetIndex) {
        PathIterator iterator = shape.getPathIterator(null);
        float[] coords = new float[6];
        int currentIndex = 0;

        while (!iterator.isDone()) {
            int type = iterator.currentSegment(coords);
            if (type == PathIterator.SEG_MOVETO || type == PathIterator.SEG_LINETO) {
                if (currentIndex == targetIndex) {
                    return new Point2D.Float(coords[0], coords[1]);
                }
                currentIndex++;
            }
            iterator.next();
        }
        throw new IndexOutOfBoundsException("Vertex index " + targetIndex + " not found.");
    }
}
