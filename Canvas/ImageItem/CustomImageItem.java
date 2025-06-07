package Canvas.ImageItem;

import java.awt.*;

public class CustomImageItem extends ImageCanvasItem {
    public CustomImageItem(Image image, Point position) {
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
        g2dCopy.translate(-width / 2, -height / 2);

//        g2dCopy.setTransform(transform);
        g2dCopy.drawImage(image, 0, 0, width, height, null);

        // Draw selection border if selected
        if (selected) {
            g2dCopy.setColor(Color.RED);
            g2dCopy.setStroke(new BasicStroke(2));
            g2dCopy.drawRect(0, 0, width, height);
        }

        g2dCopy.dispose();
    }

    public void move(int dx, int dy) {
        this.position.x += dx;
        this.position.y += dy;
    }

}
