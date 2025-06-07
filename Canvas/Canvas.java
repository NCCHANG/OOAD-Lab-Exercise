package Canvas;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

abstract public class Canvas extends JPanel {
    private int width;
    private int height;

    protected float rotation = 0.0f;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
//        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        repaint();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        // Apply canvas rotation
        if (rotation != 0) {
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;

            g2d.translate(centerX, centerY);
            g2d.rotate(Math.toRadians(rotation));
            g2d.translate(-centerX, -centerY);
        }

        paintCanvas(g2d);
        g2d.dispose();
    }

    protected abstract void paintCanvas(Graphics g);


    public double getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;

        repaint();
    }

    // Transform screen coordinates to canvas coordinates
    public Point transformPointToCanvas(Point screenPoint) {
        if (rotation == 0) {
            return screenPoint;
        }

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Create inverse transform to "undo" the canvas rotation
        AffineTransform transform = new AffineTransform();
        transform.translate(centerX, centerY);
        transform.rotate(-Math.toRadians(rotation)); // Negative to invert
        transform.translate(-centerX, -centerY);

        Point.Double canvasPoint = new Point.Double();
        transform.transform(
                new Point.Double(screenPoint.x, screenPoint.y),
                canvasPoint
        );
        return new Point((int) canvasPoint.x, (int) canvasPoint.y);

    }
}
