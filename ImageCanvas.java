
// import item.DisplayableItem; // Removed or update this line if DisplayableItem is in a different package

import javax.swing.*;

import item.DisplayableItem;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class ImageCanvas extends JPanel {
    private DisplayableItem selectedItem;
    private final List<DisplayableItem> displayItems = new ArrayList<>();
    private double overallCanvasRotationAngle = 0;

    public ImageCanvas(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.LIGHT_GRAY);
        setOpaque(true);
    }


public ImageCanvas(int width, int height, String imagePath) {
    this(width, height);
    System.out.println("ImageCanvas created with path: " + imagePath + " (currently not used)");
}


    public void addDisplayItem(DisplayableItem item) {
        if (item != null) {
            this.displayItems.add(item);
            repaint();
        }
    }

    public void removeDisplayItem(DisplayableItem item) {
        if (item != null) {
            this.displayItems.remove(item);
            repaint();
        }
    }

    public DisplayableItem getItemAt(Point p) {
        for (int i = displayItems.size() - 1; i >= 0; i--) {
            DisplayableItem item = displayItems.get(i);
            if (item.contains(p)) {
                return item;
            }
        }
        return null;
    }

    public void bringToFront(DisplayableItem item) {
        if (item != null && displayItems.contains(item)) {
            displayItems.remove(item);
            displayItems.add(item);
            repaint();
        }
    }

    public void selectItemAt(int x, int y) {
        for (int i = displayItems.size() - 1; i >= 0; i--) {
            DisplayableItem item = displayItems.get(i);
            if (item.contains(new Point(x, y))) {
                this.selectedItem = item;
                return;
            }
        }
        this.selectedItem = null;
    }

    public DisplayableItem getSelectedItem() {
        return selectedItem;
    }

    public List<DisplayableItem> getDisplayItems() {
        return new ArrayList<>(displayItems); // Return a copy to avoid outside modification
    }

    public void setRotationAngle(double angle) {
        this.overallCanvasRotationAngle = angle;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        int w = getWidth();
        int h = getHeight();

        // Enable smooth rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // Apply canvas-wide rotation (around center)
        if (overallCanvasRotationAngle != 0) {
            AffineTransform transform = AffineTransform.getRotateInstance(
                    Math.toRadians(overallCanvasRotationAngle), w / 2.0, h / 2.0);
            g2d.transform(transform);
        }

        // Draw all items
        for (DisplayableItem item : displayItems) {
            item.draw(g2d);
        }

        g2d.dispose();
    }
}
