package Canvas;

import java.awt.*;

public abstract class ImageCanvasItem {
    protected Point position;
    protected Image image;
    protected double rotation = 0.0;

    public ImageCanvasItem(Image image, Point position) {
        this.image = image;
        this.position = position;
    }

    public abstract void draw(Graphics g);
    public abstract boolean contains(Point p);
    public void setPosition(Point p) {
        this.position = p;
    }
}
