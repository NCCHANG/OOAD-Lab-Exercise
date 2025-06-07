package Canvas.ImageItem;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public abstract class ImageCanvasItem {
    protected Point position;
    protected Image image;

    protected boolean selected = false;

    // width && height
    protected int width;
    protected int height;

    protected float rotation = 0.0f;

    public ImageCanvasItem(Image image, Point position) {
        this.image = image;
        this.position = position;

        if (image != null) {
            this.width = image.getWidth(null);
            this.height = image.getHeight(null);
        }
    }

    public abstract void draw(Graphics g);

    public boolean contains(Point p) {
        // Create transform that matches how we draw the item
        AffineTransform transform = new AffineTransform();
        // 3. Translate the rotated image so its center is at its final canvas position.
        transform.translate(position.x + width / 2.0, position.y + height / 2.0);
        // 2. Rotate around the origin (which is now the image's center).
        transform.rotate(rotation);
        // 1. Translate the image so its center is at the origin (0,0) for rotation.
        transform.translate(-width / 2.0, -height / 2.0);
        try {
            // Create inverse transform
            AffineTransform inverseTransform = transform.createInverse();

            // Transform the input point to the object's coordinate system
            // Use Point2D.Double for precision.
            Point2D.Double clickPointCanvasCoords = new Point2D.Double(p.x, p.y);
            Point2D.Double objectPointLocalCoords = new Point2D.Double();

            inverseTransform.transform(clickPointCanvasCoords, objectPointLocalCoords);

            // Check if point is within the original bounds
            return objectPointLocalCoords.x >= 0 && objectPointLocalCoords.x < width &&
                    objectPointLocalCoords.y >= 0 && objectPointLocalCoords.y < height;

        } catch (NoninvertibleTransformException e) {
            return false;
        }
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point p) {
        this.position = p;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, width, height);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
