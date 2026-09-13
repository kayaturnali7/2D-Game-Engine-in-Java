package creator.core;

import creator.model.Point;
import creator.model.Shape;
import creator.util.Util;

import java.awt.Graphics2D;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

public class ShapeController {
    public static final int MAX_BOX_SIZE = 150;
    public static final int SIZE_INCREMENT = 10;

    private static final ArrayList<Shape> shapes = new ArrayList<>();
    private static Shape selectedShape, hoveredShape, deletedShape;
    private static Point hoveredPoint;

    public static void update() {
        hoveredShape = null;
        hoveredPoint = null;
        for (Shape shape : shapes) {
            shape.update();
        }
        shapes.removeIf(shape->shape.getPoints().isEmpty() || !shape.isAlive());
    }

    public static void draw(Graphics2D g2d) {
        for (Shape shape : shapes) {
            shape.draw(g2d);
        }
    }

    public static void createNewShape(int x, int y) {
        if (hoveredShape != null) return;

        Shape newShape = new Shape(x, y);

        shapes.add(newShape);
        selectedShape = newShape;
    }

    public static Shape getSelected() {
        if (selectedShape == null) return null;
        return selectedShape;
    }

    public static void deleteShape(Shape shape){
        if (hoveredShape == null) return;
        deletedShape = shape;
        shape.kill();
        if (selectedShape != null && shape == selectedShape){
            selectedShape = null;
        }
    }

    public static void undoDelete() {
        if (deletedShape == null) return;

        selectedShape = deletedShape;
        selectedShape.revive();

        shapes.add(selectedShape);
        deletedShape = null;
    }

    public static void toggleBoxVisibility() {
        selectedShape.toggleDrawBoxes();
    }

    public static void incrementBoxSize(int direction){
        if (selectedShape == null) return;
        if (direction > 0) {
            selectedShape.increaseBoxSize();
        }else selectedShape.decreaseBoxSize();
    }

    public static void addPoint(int X, int Y) {
        if (hoveredShape != null && hoveredShape != selectedShape) return;
        selectedShape.addPoint(X, Y);
    }

    public static void deletePoint(){
        if (hoveredPoint == null) return;
        hoveredPoint.kill();
    }

    public static void setSelectedShape(Shape shape) {
        if (shape == hoveredShape && hoveredShape == null) return;
        selectedShape = shape;

        if (selectedShape == null) return;
        if (!selectedShape.isDrawingBoxes()) selectedShape.toggleDrawBoxes();
        deletedShape = null;
    }

    public static Shape getHoveredShape() {
        return hoveredShape;
    }

    public static Point getHoveredPoint(){
        return hoveredPoint;
    }

    public static void setHoveredPoint(Point point){
        hoveredPoint = point;
    }

    public static void setHoveredShape(Shape shape){
        hoveredShape = shape;
    }

    public static void export(){
        if (selectedShape == null) return;
        Path path = Paths.get("src/shapecreator/output/shapes.txt");

        ArrayList<Point> points = selectedShape.getPoints();

        int n = points.size();
        int[] xPoints = new int[n];
        int[] yPoints = new int[n];

        for (int i = 0; i<n; i++){
            Point point = points.get(i);

            int x = point.getLocalX();  // implement the coordinate transform because values are being offset a little because of anchor x and y point
            int y = point.getLocalY();

            xPoints[i] = x;
            yPoints[i] = y;
        }


        String xList = Arrays.toString(xPoints);
        String yList = Arrays.toString(yPoints);

        xList = Util.replaceBrackets(xList);
        yList = Util.replaceBrackets(yList);

        String content = "int [] xPoints = new int[] " + xList + ";" +
                         "\nint [] yPoints = new int[] " + yList + ";\n\n";

        try {
            // Appends the string directly to the file
            Files.writeString(path, content, StandardOpenOption.APPEND);
            System.out.println("Shape exported");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

    }
}
