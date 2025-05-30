package Canvas;
import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ImageCanvas extends JPanel {
    private List<ImageCanvasItem> items = new ArrayList<>();
    
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

    @Override
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (ImageCanvasItem item : items) {
            item.draw(g); // Assumes draw(Graphics) is implemented in subclasses
        }
    }
}
