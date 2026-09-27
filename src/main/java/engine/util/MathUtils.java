package engine.util;

public class MathUtils {
    public static double pythagorean(double a, double b) {
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }


    public static double sineWave(double angle, double amplitude, double frequency){
        return amplitude * Math.sin(Math.toRadians(angle) * frequency);
    }
}

