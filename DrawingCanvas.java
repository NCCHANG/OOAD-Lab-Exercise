import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingCanvas extends Canvas implements MouseListener, MouseMotionListener {
    Point oldPnt = new Point();
    Point newPnt = new Point();
    Image image;
    Graphics2D g2d;
    int penStroke = 4;
    Color penColor = Color.black;

    DrawingCanvas(int width, int height) {
        super(width, height);
        setBackground(Color.white);
        setVisible(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    @Override
    protected void paintComponent(Graphics g){
        if(image == null){
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

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {
        oldPnt = e.getPoint();
    }
}