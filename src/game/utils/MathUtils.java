package game.utils;

public class MathUtils {
    public static float pythagorean(float a, float b) {
        return (float) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    public static double wrapAngle(double angle) {
        return ((angle % 360) + 360) % 360;
    }
}

