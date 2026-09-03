import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Player {
    class Controller implements KeyListener {

        public boolean W_pressed, A_pressed, S_pressed, D_pressed, Space_pressed;

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();

            if (code == KeyEvent.VK_W){
                W_pressed = true;

            }
            if (code == KeyEvent.VK_A){
                A_pressed = true;
            }
            if (code == KeyEvent.VK_S){
                S_pressed = true;
            }
            if (code == KeyEvent.VK_D){
                D_pressed = true;
            }
            if (code == KeyEvent.VK_SPACE){
                Space_pressed = true;
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int code = e.getKeyCode();

            if (code == KeyEvent.VK_W){
                W_pressed = false;
            }
            if (code == KeyEvent.VK_A){
                A_pressed = false;
            }
            if (code == KeyEvent.VK_S){
                S_pressed = false;
            }
            if (code == KeyEvent.VK_D){
                D_pressed = false;
            }
            if (code == KeyEvent.VK_SPACE){
                Space_pressed = false;
            }

        }

        public void update(){
            if (A_pressed){
                transform.rotate(-ANGULARSPEED);
            } else if (D_pressed){
                transform.rotate(ANGULARSPEED);
            }

            if (W_pressed){
                physics.applyAcceleration();
                transform.setMoveDirection(transform.getDirection());
            }
        }
    }
    class Transform{

        private float x = 400;
        private float y = 400;

        private double rotation = 0;

        private double shipDirection = rotation + 90;
        private double moveDirection = rotation;

        private final int scale = 1;

        private Shape activeShape;

        public Transform(){
        }

        private void update(){
            wrapRotation();
            translate();
        }

        private void translate(){
            x -= physics.getVelocityX();
            y -= physics.getVelocityY();
        }

        private void rotate(double amount){
            rotation += amount;
            shipDirection = rotation + 90;
        }

        private void rotateTo(double angle){
            rotation = angle;
            shipDirection = rotation + 90;
            setMoveDirection(rotation);
        }

        private void translateTo(float X, float Y){
            x = X;
            y = Y;
        }

        private void setMoveDirection(double angle){
            moveDirection = angle;
        }

        private double getDirection(){return shipDirection;}

        private void wrapRotation(){
            rotation = rotation % 360.0;
            if (rotation < 0) {
                rotation += 360.0;
            }

            shipDirection = shipDirection % 360.0;
            if (shipDirection < 0) {
                shipDirection += 360.0;
            }

            moveDirection = moveDirection % 360.0;
            if (moveDirection < 0) {
                moveDirection += 360.0;
            }
        }

        public void updateActiveShape(Shape base_ship){
            AffineTransform tx = new AffineTransform();

            tx.translate(x,y);
            tx.rotate(Math.toRadians(rotation));

            activeShape = tx.createTransformedShape(base_ship);
        }

        private Shape getAciveShape(){return activeShape;}

        public Rectangle2D getBounds(){return activeShape.getBounds2D();};

        public int getScale(){return scale;}

        private float getX(){return x;}

        private float getY(){return y;}

    }
    class PhysicsBody{

        private final float DRAG = 0.97F;

        private float thrustX;
        private float thrustY;

        private float velocityX;
        private float velocityY;

        private void applyAcceleration(){
            thrustX = (float) Math.cos(Math.toRadians(transform.shipDirection)) * THRUST;
            thrustY = (float) Math.sin(Math.toRadians(transform.shipDirection)) * THRUST;

            velocityX += thrustX;
            velocityY += thrustY;

            float velocity = getVelocity();

            if (velocity > MAXSPEED) {
                velocityX = (velocityX / velocity) * MAXSPEED;
                velocityY = (velocityY / velocity) * MAXSPEED;
            }
        }

        private void applyDrag(){
            velocityX *= DRAG;
            velocityY *= DRAG;

            if (getVelocity() < 0.01f) {
                velocityX = 0;
                velocityY = 0;
            }
        }

        public float getVelocityX() {
            return velocityX;
        }

        public float getVelocityY() {return velocityY;}

        public float getVelocity(){return MathUtils.pythagorean(velocityX, velocityY);}

        private void update(){
            applyDrag();
        }
    }
    class Collider{

        public boolean isOutsideBounds(){
            Rectangle2D shipBounds = transform.getBounds();
            Rectangle2D screenBounds = new Rectangle2D.Double(0,0,GamePanel.SCREEN_WIDTH,GamePanel.SCREEN_HEIGHT);
            if (!shipBounds.intersects(screenBounds)){
                return true;
            }else return false;
        }

        private void checkBounds(){
            int screen_width = GamePanel.SCREEN_WIDTH;
            int screen_height = GamePanel.SCREEN_HEIGHT;

            Rectangle2D bounds = transform.getBounds();

            double x = transform.getX();
            double y = transform.getY();

            if (isOutsideBounds()){
                if (x<0 && y<0){ //                                     TOP LEFT CORNER
                    transform.translateTo(screen_width, screen_height);
                } else if (x>screen_width && y<0) { //                  TOP RIGHT CORNER
                    transform.translateTo(0, screen_height);
                } else if (x<0 && y>screen_height){ //                  BOTTOM LEFT CORNER
                    transform.translateTo(screen_width, 0);
                } else if (x>screen_width && y > screen_height) { //    BOTTOM RIGHT CORNER
                    transform.translateTo(0,0);
                }

                if (x<0){//                                             LEFT BORDER
                    transform.translateTo(screen_width, (float) y);
                } else if (x > screen_width) {//                        RIGHT BORDER
                    transform.translateTo(0, (float) y);
                }

                if (y<0){//                                             TOP BORDER
                    transform.translateTo((float) x, screen_width);
                } else if (y>screen_height) {//                         BOTTOM BORDER
                    transform.translateTo((float) x, 0);
                }

            }

        }

        public void update(){
            checkBounds();
        }

    }
    class Renderer{
        private Polygon baseShip;

        public Renderer(){
            createShip();
            transform.updateActiveShape(baseShip);
        }

        public void update(){
            transform.updateActiveShape(baseShip);
        }

        private void createShip() {
            int side = transform.getScale() * 60;
            int base = (int) (side * (7.0 / 9.0));
            double height = Math.sqrt((Math.pow(side, 2)) - Math.pow(((double)base / 2), 2));

            double dTop = height * ((double) 2 / 3);
            double dBase = height * ((double) 1 / 3);

            int v1x = 0;
            int v1y = (int) (-dTop);

            int v2x = -(base / 2);
            int v2y = (int) (dBase);

            int v3x = (base / 2);
            int v3y = (int) (dBase);

            // TOP, BOTTOM-LEFT, BOTTOM-RIGHT
            int[] xPoints = new int[]{v1x, v2x, v3x};
            int[] yPoints = new int[]{v1y, v2y, v3y};

            int numPoints = 3;

            baseShip = new Polygon(xPoints, yPoints, numPoints);
        }

        private void draw(Graphics2D g2d){
            Shape ship = transform.getAciveShape();

            g2d.setColor(COLOR);
            g2d.draw(ship);
        }

    }

    private final Controller controller = new Controller();
    private final Transform transform = new Transform();
    private final PhysicsBody physics = new PhysicsBody();
    private final Collider collider = new Collider();
    private final Renderer renderer = new Renderer();

    private final Color COLOR = Color.white;
    private final int MAXSPEED = 7;
    private final int ANGULARSPEED = 4;
    private final float THRUST = 0.2F;

    public void update(){
        controller.update();
        collider.update();
        physics.update();
        transform.update();
        renderer.update();
    }

    public void draw(Graphics2D g2d){
        renderer.draw(g2d);
    }

    public Controller getController(){return controller;}
}
