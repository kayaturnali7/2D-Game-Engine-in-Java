package engine.util;

import engine.model.Vector2;

public class MathUtils {
    public static double pythagorean(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }

    public static double distance2D(Vector2 point1, Vector2 point2){
        double x1 = point1.getX();
        double y1 = point1.getY();

        double x2 = point2.getX();
        double y2 = point2.getY();

        return Math.sqrt(((x2-x1)*(x2-x1)) + ((y2-y1)*(y2-y1)));
    }

}

