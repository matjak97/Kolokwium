package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AnimationCanva extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    //Buffer
    Image image;

    Graphics2D device;

    Graphics2D buffer;

    private int ax, ay, bx, by;
    private ArrayList<Prostokat> prostokaty;

    public AnimationCanva() {
        super();
        setBackground(Color.WHITE);
        prostokaty = new ArrayList<>();
    }

    public void initialize() {
        int width = getWidth();
        int height = getHeight();

        image = createImage(width, height);
        buffer = (Graphics2D) image.getGraphics();
        buffer.setBackground(Color.WHITE);
        device = (Graphics2D) getGraphics();
        Prostokat.setCanva(buffer, width, height);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ax = (int) getMousePosition().getX();
                ay = (int) getMousePosition().getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                bx = (int) getMousePosition().getX();
                by = (int) getMousePosition().getY();
                Prostokat rectangle = addNewShape();
                if(prostokaty.contains(rectangle)) {
                    Thread thread = new Thread(rectangle);
                    thread.start();
                }
            }
        });
    }

    private Prostokat addNewShape() {
        Prostokat rectangle = new Prostokat(calculateLocation(), calculateSize(), this);
        if(!newlyAddedCollision(rectangle))
            prostokaty.add(rectangle);
        return rectangle;
    }

    private Point calculateLocation() {
        Point result = new Point();
        result.x = Math.min(ax, bx);
        result.y = Math.min(ay, by);
        return result;
    }

    private Dimension calculateSize() {
        Dimension result = new Dimension();
        result.setSize(Math.abs(ax - bx), Math.abs(ay - by));
        return result;
    }

    private boolean newlyAddedCollision(Prostokat shape) {
        for(Prostokat rectangle:prostokaty) {
            if(rectangle.intersects(shape))
                return true;
        }
        return false;
    }

    public boolean movementCollision(Prostokat shape) {
        for(Prostokat rectangle:prostokaty) {
            if(rectangle != shape && rectangle.intersects(shape))
                return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Image image = createImage(getWidth(), getHeight());
        image.getGraphics().drawImage(this.image, 0,0,null);
        buffer = (Graphics2D) image.getGraphics();
        this.image = image;
        Prostokat.setCanva(buffer, getWidth(), getHeight());
    }
}
