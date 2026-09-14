package engine.entity;

import engine.display.CorePanel;
import java.awt.geom.Rectangle2D;

public class Bounds {
    private final Entity currentEntity;

    private final int screenWidth = CorePanel.SCREEN_WIDTH;
    private final int screenHeight = CorePanel.SCREEN_HEIGHT;

    private boolean leftBound, rightBound, topBound, bottomBound;
    private boolean outOfBounds;

    protected Bounds(Entity entity){
        currentEntity = entity;
    }

    protected void update(){
        Rectangle2D bounds = currentEntity.getGeometry().bounds();

        double width = bounds.getWidth()/2;
        double height = bounds.getHeight()/2;

        float x = currentEntity.getPosition().x();
        float y = currentEntity.getPosition().y();

        updateBounds(x,y,width,height);

        if (outOfBounds){
            teleport(x,y,width,height);
        }
    }

    private void updateBounds(float x, float y, double width, double height){
        leftBound = x + width < 0;
        rightBound = x - width > screenWidth;
        topBound = y + height < 0;
        bottomBound = y - height > screenHeight;
        outOfBounds = leftBound || rightBound || topBound || bottomBound;
    }

    private void teleport(float x, float y, double width, double height){
        if (leftBound){
            currentEntity.moveTo((float) (screenWidth+width), y);
        } else if (rightBound) {
            currentEntity.moveTo((float) -width, y);
        }

        if (topBound){
            currentEntity.moveTo(x, (float) (screenHeight+height));
        } else if (bottomBound){
            currentEntity.moveTo(x, (float) -height);
        }
    }
}
