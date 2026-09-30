package engine.util;

import engine.model.Vector2D;

public class MathUtils {
    public static double pythagorean(double a, double b) {
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    public static double distance2D(Vector2D point1, Vector2D point2){
        double x1 = point1.getX();
        double y1 = point1.getY();

        double x2 = point2.getX();
        double y2 = point2.getY();

        return Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
    }

    public static double sineWave(double angle, double amplitude, double frequency){
        return amplitude * Math.sin(Math.toRadians(angle) * frequency);
    }


}

