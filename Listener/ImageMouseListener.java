package Listener;

import Canvas.ImageCanvas;
import Canvas.ImageItem.AnimalItem;
import Canvas.ImageItem.CustomImageItem;
import Canvas.ImageItem.FlowerItem;
import Canvas.ImageItem.ImageCanvasItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


public class ImageMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {

    private ImageCanvas canvas;
    private Point dragStartPoint = null;
    private ImageCanvasItem selectedItem;
    private static final double ROTATION_STEP = Math.PI / 18; // 10 degrees in radians

    public ImageMouseListener(ImageCanvas canvas) {
        // Constructor can be used to initialize any required fields or listeners
        this.canvas = canvas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (selectedItem != null) {
            // Different actions based on item type
            if (selectedItem instanceof AnimalItem) {
                AnimalItem animalItem = (AnimalItem) selectedItem;
                animalItem.flip();
            } else if (selectedItem instanceof FlowerItem) {
                FlowerItem flowerItem = (FlowerItem) selectedItem;

                // left click scales up, right click scales down
                if (e.getButton() == MouseEvent.BUTTON1) {
                    flowerItem.scale(1.2f); // Scale up by 20%
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    flowerItem.scale(0.8f); // Scale down by 20%
                }
            }
            canvas.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Get canvas-relative coordinates (get clicked position in canvas coordinates, the value get is the rotated coordinate)
        Point canvasPoint = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), canvas);

        // Find clicked item (reverse order for top-most item)
        selectedItem = null;
        List<ImageCanvasItem> items = canvas.getItems();

        for (int i = items.size() - 1; i >= 0; i--) {
            ImageCanvasItem item = items.get(i);
            if (item.contains(canvasPoint)) {
                selectedItem = item;
                item.setSelected(true);
                break;
            }
        }

        // Deselect other items
        for (ImageCanvasItem item : items) {
            if (item != selectedItem) {
                item.setSelected(false);
            }
        }

        if (selectedItem != null) {
            dragStartPoint = e.getPoint();
//            dragging = true;
        }

        canvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragStartPoint = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (selectedItem != null && dragStartPoint != null && selectedItem instanceof CustomImageItem) {
            // Get canvas-relative coordinates
            Point canvasPoint = SwingUtilities.convertPoint(
                    e.getComponent(), e.getPoint(), canvas);

            // Transform coordinates accounting for canvas rotation
            Point transformedPoint = canvas.transformPointToCanvas(canvasPoint);

            // Calculate drag distance in canvas coordinates
            int dx = transformedPoint.x - dragStartPoint.x;
            int dy = transformedPoint.y - dragStartPoint.y;

            CustomImageItem selectedItem = (CustomImageItem) this.selectedItem;
            selectedItem.move(dx, dy);

            dragStartPoint = transformedPoint;
            canvas.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (selectedItem != null) {
            if (e.isControlDown()) {
                // Ctrl + wheel rotates the item
                float rotation = selectedItem.getRotation();
                if (e.getWheelRotation() < 0) {
                    // Rotate counter-clockwise
                    rotation += ROTATION_STEP;
                } else {
                    // Rotate clockwise
                    rotation -= ROTATION_STEP;
                }
                selectedItem.setRotation(rotation);
                canvas.repaint();
            } else if (selectedItem instanceof FlowerItem) {
                // Wheel without Ctrl scales the FlowerItem
                FlowerItem flowerItem = (FlowerItem) selectedItem;
                if (e.getWheelRotation() < 0) {
                    flowerItem.scale(1.1f); // Scale up
                } else {
                    flowerItem.scale(0.9f); // Scale down
                }
                canvas.repaint();
            }
        }
    }

}
