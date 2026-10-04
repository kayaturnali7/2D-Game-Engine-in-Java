package engine.util;

public class VectorMath {
    public static double[] rotation(double initialX, double initialY, double angle){
        double cos = Math.cos(Math.toRadians(angle));
        double sin = Math.sin(Math.toRadians(angle));

        double xPrime = rotationX(initialX, initialY, sin, cos);
        double yPrime = rotationY(initialX, initialY, sin, cos);

        return new double[]{xPrime,yPrime};
    }

    public static double rotationX(double initialX, double initialY, double sin, double cos){
        return initialX * cos - initialY * sin;
    }

    public static double rotationY(double initialX, double initialY, double sin, double cos){
        return initialX * cos - initialY * sin;
    }

}
