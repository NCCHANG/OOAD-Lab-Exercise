package Canvas.ImageItem;

import java.awt.*;

public class FlowerItem extends ImageCanvasItem {

    private float scaleFactor = 1.0f;

    public FlowerItem(Image image, Point position) {
        super(image, position);
    }

    @Override
    public void draw(Graphics g) {
        if (image == null) return;

        Graphics2D g2dCopy = (Graphics2D) g.create();

//        g2dCopy.translate();

        // Apply transformations
//        AffineTransform transform = new AffineTransform();
        g2dCopy.translate(position.x + width / 2, position.y + height / 2);
        g2dCopy.rotate(rotation);
        g2dCopy.scale(scaleFactor, scaleFactor);
        g2dCopy.translate(-width / 2, -height / 2);

//        g2dCopy.setTransform(transform);
        g2dCopy.drawImage(image, 0, 0, width, height, null);

        // Draw selection border if selected
        if (selected) {
            g2dCopy.setColor(Color.GREEN);
            g2dCopy.setStroke(new BasicStroke(2));
            g2dCopy.drawRect(0, 0, width, height);
        }

        g2dCopy.dispose();
    }

    public void scale(float factor) {
        scaleFactor *= factor;

        // Keep within reasonable bounds
        scaleFactor = Math.max(0.1f, Math.min(5.0f, scaleFactor));

        // Update width and height based on the new scale factor and original image size
        width = (int) (image.getWidth(null) * scaleFactor);
        height = (int) (image.getHeight(null) * scaleFactor);

//        System.out.println("FlowerItem scaled to: " + scaleFactor + ", new size: " + width + "x" + height);
    }
}
