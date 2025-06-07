package Canvas;

import Canvas.ImageItem.ImageCanvasItem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ImageCanvas extends Canvas {
    private List<ImageCanvasItem> items = new ArrayList<>();

    public ImageCanvas(int width, int height) {
        super(width, height);
//        setPreferredSize(new Dimension(width, height));
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
    protected void paintCanvas(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (ImageCanvasItem item : items) {
            item.draw(g2d);
        }
    }
}
