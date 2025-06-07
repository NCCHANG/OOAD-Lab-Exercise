package Canvas.ImageItem;

import java.awt.*;

public class AnimalItem extends ImageCanvasItem {

    private boolean flipped = false; // Indicates if the image is flipped horizontally

    public AnimalItem(Image image, Point position) {
        super(image, position);
    }

    @Override
    public void draw(Graphics g) {
        if (image == null) return;

        Graphics2D g2dCopy = (Graphics2D) g.create();

        // Apply transformations
//        AffineTransform transform = new AffineTransform();
        g2dCopy.translate(position.x + width / 2, position.y + height / 2);
        g2dCopy.rotate(rotation);
        if (flipped) {
            g2dCopy.scale(-1, 1);
        }
        g2dCopy.translate(-width / 2, -height / 2);

//        g2dCopy.setTransform(transform);
        g2dCopy.drawImage(image, 0, 0, width, height, null);

        // Draw selection border if selected
        if (selected) {
            g2dCopy.setColor(Color.BLUE);
            g2dCopy.setStroke(new BasicStroke(2));
            g2dCopy.drawRect(0, 0, width, height);
        }

        g2dCopy.dispose();
    }

    public void flip() {
        flipped = !flipped; // Toggle the flipped state
    }
}
