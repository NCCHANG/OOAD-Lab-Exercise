package item;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class AnimalItem implements DisplayableItem {
    private Image image;
    private int x, y;
    private double rotation = 0;

    public AnimalItem(Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform old = g2d.getTransform();
        g2d.translate(x, y);
        g2d.rotate(Math.toRadians(rotation), image.getWidth(null) / 2.0, image.getHeight(null) / 2.0);
        g2d.drawImage(image, 0, 0, null);
        g2d.setTransform(old);
    }

    @Override
    public boolean contains(Point p) {
        Rectangle bounds = new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
        return bounds.contains(p);
    }

    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public void rotate(double angle) {
        this.rotation = angle;
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }

    @Override
    public void flip() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'flip'");
    }

    @Override
    public Point getCenter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCenter'");
    }

    @Override
    public Point getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }

    @Override
    public void setPosition(Point p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPosition'");
    }

    @Override
    public double getRotation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRotation'");
    }

    @Override
    public void setRotation(double angle) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRotation'");
    }

    @Override
    public double getScale() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getScale'");
    }

    @Override
    public void setScale(double scale) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setScale'");
    }
}
