package Canvas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class DrawingCanvas extends Canvas implements MouseMotionListener {
    Point oldPnt = new Point();
    Point newPnt = new Point();
    Image image;
    Graphics2D g2d;
    int penStroke = 4;
    Color penColor = Color.black;

    public DrawingCanvas(int width, int height) {
        super(width, height);
        setVisible(true);
        this.addMouseMotionListener(this);
    }

    @Override
    protected void paintCanvas(Graphics g) {
        if (image == null) { //when minimizing, create a new image for saving the drawing
            image = createImage(getWidth(), getHeight());
            g2d = (Graphics2D) image.getGraphics();
        } else {
            g.drawImage(image, 0, 0, null);
        }
    }

    public void mouseDragged(MouseEvent e) {
        g2d.setStroke(new BasicStroke(penStroke));
        g2d.setColor(penColor);
        newPnt = e.getPoint();
        g2d.drawLine(oldPnt.x, oldPnt.y, newPnt.x, newPnt.y);
        repaint();
        oldPnt = newPnt;
    }

    public void mouseMoved(MouseEvent e) {
        oldPnt = e.getPoint();
    }

    public void setPenStroke(int penStroke) {
        this.penStroke = penStroke;
    }

    public void setPenColor(Color penColor) {
        this.penColor = penColor;
    }

    public Color getPenColor() {
        return penColor;
    }

    public int getPenStroke() {
        return penStroke;
    }
}