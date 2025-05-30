package Button;

import javax.swing.*;

public class LibraryCollectionButton extends JButton{
    private String id;
    private int width;
    private int height;

    public LibraryCollectionButton(String text, int width, int height) {
        super(text);
        this.width = width;
        this.height = height;
        setSize(width, height);
    }

    public LibraryCollectionButton(Icon icon, int width, int height) {
        super(icon);
        this.width = width;
        this.height = height;
        setSize(width, height);
    }

    public LibraryCollectionButton(String text, Icon icon, int width, int height) {
        super(text, icon);
        this.width = width;
        this.height = height;
        setSize(width, height);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
}

