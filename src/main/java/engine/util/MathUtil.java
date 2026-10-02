package engine.util;

import engine.data.Vector2;

public class MathUtil {
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

    public static Vector2 getMin(int[] verticesX, int[] verticesY){
        int minX = verticesX[0];
        int minY = verticesY[0];

        for (int i = 1; i < verticesX.length; i++){
            int currentX = verticesX[i];
            int currentY = verticesY[i];

            if (currentX < minX){
                minX = currentX;
            }

            if (currentY < minY){
                minY = currentY;
            }
        }

        return new Vector2(minX,minY);
    }

    public static Vector2 getMax(int[] verticesX, int[] verticesY){
        int maxX = verticesX[0];
        int maxY = verticesY[0];

        for (int i = 1; i < verticesX.length; i++){
            int currentX = verticesX[i];
            int currentY = verticesY[i];

            //maxX
            if (currentX > maxX){
                maxX = currentX;
            }

            //maxY
            if (currentY > maxY){
                maxY = currentY;
            }
        }

        return new Vector2(maxX,maxY);
    }

}

