package Canvas;
import javax.swing.*;

import java.awt.*;

abstract public class Canvas extends JPanel{
    private int width;
    private int height;

    Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        setSize(width, height);
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    protected abstract void paintComponent(Graphics g);
}
