// ImageMouseListener.java
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import item.DisplayableItem;

import java.awt.Cursor; // For changing cursor appearance

/*
 * Assumptions for this listener to work correctly:
 *
 * 1.  ImageCanvas class:
 * -   Manages a list of `DisplayableItem` objects.
 * -   `public DisplayableItem getItemAt(Point p)`: Returns the topmost item at point p, or null.
 * -   `public void repaint()`: Triggers a redraw of the canvas.
 * -   `public void setCursor(Cursor cursor)`: To change mouse cursor.
 * -   `public void bringToFront(DisplayableItem item)`: (Optional) Moves item to top of draw order.
 *
 * 2.  DisplayableItem interface (or abstract class) is defined as expected.
 */

public class ImageMouseListener implements MouseListener, MouseMotionListener {

    private ImageCanvas imageCanvas; // The canvas this listener is attached to
    private DisplayableItem selectedItem; // The item currently being interacted with
    private Point lastMousePosition;      // For calculating drag distances/angles
    private Point dragStartOffset;        // Offset from item's origin when dragging (for moving)

    // For scaling:
    private Point itemCenterForScaling;   // Center of the item when scaling starts
    private double initialDistanceToCenter; // Distance from mouse to item center at drag start for scaling
    private double initialItemScale;        // Item's scale at drag start for scaling

    // For rotation:
    private Point itemCenterForRotation;    // Center of the item when rotation starts
    private double initialAngleToCenterRad; // Angle from item center to mouse at drag start for rotation
    private double initialItemRotationDeg;  // Item's rotation at drag start for rotation

    private enum InteractionMode {
        NONE,
        MOVING,    // For transposing custom images (and potentially others) [cite: 31]
        SCALING,   // For scaling flower images [cite: 31]
        ROTATING   // For gesture-based rotation [cite: 28]
    }
    private InteractionMode currentMode = InteractionMode.NONE;

    public ImageMouseListener(ImageCanvas canvas) {
        this.imageCanvas = canvas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (imageCanvas == null) return;
        // For selection or specific click actions like flip
        DisplayableItem item = imageCanvas.getItemAt(e.getPoint());

        if (item != null) {
            // Requirement: Flip animal images (e.g., on a simple left click) [cite: 31]
            if ("animal".equalsIgnoreCase(item.getType())) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) { 
                    item.flip();
                    imageCanvas.repaint();
                }
            }
            // Could add logic here to "select" an item for property changes via toolbar
            // For now, selection is mainly for drag operations initiated in mousePressed.
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (imageCanvas == null) return;

        lastMousePosition = e.getPoint();
        selectedItem = imageCanvas.getItemAt(lastMousePosition);

        if (selectedItem != null) {
            imageCanvas.bringToFront(selectedItem); // Bring to front visually and for future clicks

            String itemType = selectedItem.getType();

            // Shift + Left Drag for ROTATING any selected item [cite: 28]
            if (e.isShiftDown() && e.getButton() == MouseEvent.BUTTON1) {
                currentMode = InteractionMode.ROTATING;
                itemCenterForRotation = selectedItem.getCenter();
                initialItemRotationDeg = selectedItem.getRotation();
                double dx = lastMousePosition.x - itemCenterForRotation.x;
                double dy = lastMousePosition.y - itemCenterForRotation.y;
                initialAngleToCenterRad = Math.atan2(dy, dx); // Current angle of mouse to item center
            }
            // Left Drag for specific actions based on type
            else if (e.getButton() == MouseEvent.BUTTON1) {
                if ("custom".equalsIgnoreCase(itemType)) {
                    // Requirement: Transpose (move) custom drawn images [cite: 31]
                    currentMode = InteractionMode.MOVING;
                    Point itemPos = selectedItem.getPosition(); // Top-left
                    // Calculate offset from item's origin to mouse click point
                    dragStartOffset = new Point(lastMousePosition.x - itemPos.x, lastMousePosition.y - itemPos.y);
                } else if ("flower".equalsIgnoreCase(itemType)) {
                    // Requirement: Scale flower images proportionally [cite: 31]
                    currentMode = InteractionMode.SCALING;
                    itemCenterForScaling = selectedItem.getCenter();
                    initialItemScale = selectedItem.getScale();
                    initialDistanceToCenter = itemCenterForScaling.distance(lastMousePosition);
                    if (initialDistanceToCenter < 1) { 
                        initialDistanceToCenter = 1; 
                    }
                } else if ("animal".equalsIgnoreCase(itemType)) {
                    // Animals are flipped on click. For dragging, let's make them movable.
                    currentMode = InteractionMode.MOVING;
                    Point itemPos = selectedItem.getPosition();
                    dragStartOffset = new Point(lastMousePosition.x - itemPos.x, lastMousePosition.y - itemPos.y);
                } else {
                     // Default to moving for other types if not specified
                     currentMode = InteractionMode.MOVING;
                     Point itemPos = selectedItem.getPosition();
                     dragStartOffset = new Point(lastMousePosition.x - itemPos.x, lastMousePosition.y - itemPos.y);
                }
            }
            imageCanvas.repaint(); 
        } else {
            currentMode = InteractionMode.NONE;
            // Potentially deselect any globally selected item if clicking on empty space
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (selectedItem == null || currentMode == InteractionMode.NONE || imageCanvas == null) {
            return;
        }

        Point currentMousePosition = e.getPoint();

        switch (currentMode) {
            case MOVING:
                int newX = currentMousePosition.x - dragStartOffset.x;
                int newY = currentMousePosition.y - dragStartOffset.y;
                selectedItem.setPosition(new Point(newX, newY));
                break;

            case SCALING:
                if (itemCenterForScaling != null) {
                    double currentDistance = itemCenterForScaling.distance(currentMousePosition);
                    if (initialDistanceToCenter > 0) { 
                        double scaleFactor = currentDistance / initialDistanceToCenter;
                        selectedItem.setScale(initialItemScale * scaleFactor);
                    }
                }
                break;

            case ROTATING:
                if (itemCenterForRotation != null) {
                    double dx = currentMousePosition.x - itemCenterForRotation.x;
                    double dy = currentMousePosition.y - itemCenterForRotation.y;
                    double currentMouseAngleRad = Math.atan2(dy, dx);
                    
                    // Calculate the change in angle from the start of the drag
                    double rotationDragAngleRad = currentMouseAngleRad - initialAngleToCenterRad;
                    // Add this change to the item's rotation at the start of the press
                    selectedItem.setRotation(initialItemRotationDeg + Math.toDegrees(rotationDragAngleRad));
                }
                break;

            case NONE:
            default:
                // Do nothing if mode is NONE
                break;
        }

        lastMousePosition = currentMousePosition;
        imageCanvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (imageCanvas == null) return;

        if (currentMode != InteractionMode.NONE) {
             imageCanvas.repaint(); // Ensure final state is painted
        }
        // Reset state
        // selectedItem = null; // Keep item selected for potential toolbar operations until another is selected or canvas is clicked
        currentMode = InteractionMode.NONE;
        dragStartOffset = null;
        itemCenterForScaling = null;
        itemCenterForRotation = null;
        // Don't reset cursor here if we want it to reflect the item under mouse in mouseMoved
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) {
        // If an item is being dragged and mouse exits, could cancel the operation
        // if (currentMode != InteractionMode.NONE) {
        //     mouseReleased(e); 
        // }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (imageCanvas == null) return;
        if (currentMode == InteractionMode.NONE) { // Only change cursor if not actively dragging/interacting
            DisplayableItem item = imageCanvas.getItemAt(e.getPoint());
            if (item != null) {
                imageCanvas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            } else {
                imageCanvas.setCursor(Cursor.getDefaultCursor());
            }
        }
    }
}