package engine.geometry;

import engine.data.Vector2;
import engine.util.GeometryUtil;

public class AABB {
    private final Vector2 min = new Vector2(0,0);
    private final Vector2 max = new Vector2(0,0);

    private int x, y;

    private int width;
    private int height;

    protected void update(int[] verticesX, int[] verticesY){
        Vector2 min = GeometryUtil.minimumVertex(verticesX, verticesY);
        Vector2 max = GeometryUtil.maximumVertex(verticesX, verticesY);

        this.min.set(min);
        this.max.set(max);

        x = (int) min.getX();
        y = (int) min.getY();

        this.width = (int) (max.getX() - min.getX());
        this.height = (int) (max.getY() - min.getY());
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Vector2 getMin(){
        return min;
    }

    public Vector2 getMax(){
        return max;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}