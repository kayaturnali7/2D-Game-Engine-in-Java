package engine.entity;

import asteroids.entities.Laser;
import engine.model.Shape;

public class Geometry {

    private final Entity entity;

    private final Shape shape;

    protected Geometry(Entity entity, Shape shape){
        this.entity = entity;
        this.shape = shape;

        double x = entity.getX();
        double y = entity.getY();
        double direction = entity.getDirection();

        shape.update(x,y,direction);


        if (entity instanceof Laser){
            System.out.println(x + " " + y);
        }


    }

    protected void update(){
        double x = entity.getX();
        double y = entity.getY();
        double direction = entity.getDirection();

        shape.update(x,y,direction);
    }

    protected Shape getShape(){
        return shape;
    }
}
