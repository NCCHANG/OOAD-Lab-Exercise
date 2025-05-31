package Canvas;
import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ImageCanvas extends JPanel {
    private List<ImageCanvasItem> items = new ArrayList<>();
    private int rotationAngle = 0;
    
    public ImageCanvas(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
    }

    public void addItem(ImageCanvasItem item) {
        items.add(item);
        repaint();
    }

    public List<ImageCanvasItem> getItems() {
        return items;
    }

    public void setRotationAngle(int angle) {
        this.rotationAngle = angle;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        // Rotate around the center of the canvas
        int cx = getWidth()/ 2;
        int cy = getHeight() / 2;
        g2d.translate(cx, cy);
        g2d.rotate(Math.toRadians(rotationAngle));
        g2d.translate(-cx, -cy);
        for (ImageCanvasItem item : items) {
            item.draw(g); // Assumes draw(Graphics) is implemented in subclasses
        }
    }
}
