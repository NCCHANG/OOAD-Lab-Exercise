package Canvas;

import java.awt.*;

public class ImageItem extends ImageCanvasItem {
    public ImageItem(Image image, Point position) {
        super(image, position);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, position.x, position.y, null);
    }

    @Override
    public boolean contains(Point p) {
        // Simple bounding box check
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        return (p.x >= position.x && p.x <= position.x + width &&
                p.y >= position.y && p.y <= position.y + height);
    }


    @Override
    public void setPosition(Point p) {
        this.position = p;
    }
    public Point getPosition() {
        return position;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
}
