package Listener;

import java.awt.event.*;
import java.util.List;

import Canvas.ImageCanvas;
import Canvas.ImageCanvasItem;

public class ImageMouseListener implements MouseListener, MouseMotionListener {

    private final ImageCanvas canvas;
    private ImageCanvasItem selectedItem;

    public ImageMouseListener(ImageCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        List<ImageCanvasItem> items = canvas.getItems();
        for (ImageCanvasItem item : items) {
            if (item.contains(e.getPoint())) {
                selectedItem = item;
                break;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (selectedItem != null) {
            selectedItem.setPosition(e.getPoint());
            canvas.repaint();
        }
    }

    @Override public void mouseReleased(MouseEvent e) { selectedItem = null; }
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
}
