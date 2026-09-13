package engine.ecs;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Geometry {
    private final Entity currentEntity;

    protected Geometry(Entity entity){
        currentEntity = entity;
    }

    private Shape baseShape;
    private Shape activeShape;

    private Area baseArea;
    private Area activeArea;

    private Rectangle2D bounds;

    protected void update(){
        updateActiveShape();
    }

    protected void createShape(int[] xPoints, int[] yPoints) {
        int numPoints = xPoints.length;

        baseShape = new Polygon(xPoints, yPoints, numPoints);
        activeShape = baseShape;

        baseArea = new Area(baseShape);
        activeArea = baseArea;

        bounds = activeShape.getBounds2D();
    }

    private void updateActiveShape(){
        double x = currentEntity.getPosition().x();
        double y = currentEntity.getPosition().y();
        double lookDirection = currentEntity.getDirection().look();

        int offset = 90;
        double direction = Math.toRadians(-lookDirection + offset);

        AffineTransform tx = new AffineTransform();
        tx.translate(x,y);
        tx.rotate(direction);

        activeShape = tx.createTransformedShape(baseShape);
        activeArea = baseArea.createTransformedArea(tx);

        bounds = activeShape.getBounds2D();
    }

    protected engine.data.Geometry getGeometry(){
        return new engine.data.Geometry(activeShape, activeArea, bounds);
    }
}
