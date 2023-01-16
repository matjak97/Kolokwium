package App;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Prostokat extends Rectangle implements Runnable, ActionListener {

    private AffineTransform aft;
    private static Graphics2D buffer;
    private Area area;
    private final AnimationCanva canva;
    private static final int delay = 10;
    private Shape shape;
    private Shape newShape;
    private boolean isMoving;
    //wymiary obszaru
    private static int w, h;
    //polozenie prostokata
    private Point location;


    public Prostokat(Point location, Dimension size, AnimationCanva canva) {
        newShape = new Rectangle2D.Float(0, 0, w, h);
        isMoving = true;
        aft = new AffineTransform();
        this.location = location;
        width = size.width;
        height = size.height;
        this.canva = canva;
    }

    @Override
    public void run() {
        while (isMoving) {
            shape = nextFrame();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Shape nextFrame() {
        aft.translate(1, 0);
        if(canva.movementCollision(this))

    }

    public static void setCanva(Graphics2D buffer, int w, int h) {
        Prostokat.w = w;
        Prostokat.h = h;
        Prostokat.buffer = buffer;
    }

    public Dimension getSize() {
        return new Dimension(width, height);
    }

    public Point getLocation() {
        return location;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        buffer.setColor(Color.BLACK);
        buffer.fill(shape);
        buffer.setColor(Color.BLACK);
        buffer.draw(shape);
    }
}
