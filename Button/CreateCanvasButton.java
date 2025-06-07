package Button;

import javax.swing.*;

public class CreateCanvasButton extends JButton {
    private int width;
    private int height;
    private String id;
    private Icon icon;
    private String text;

    public CreateCanvasButton(String text, int width, int height) {
        super(text);
        this.text = text;
        this.width = width;
        this.height = height;
        setSize(width, height);
    }

    public CreateCanvasButton(Icon icon, int width, int height) {
        super(icon);
        this.icon = icon;
        this.width = width;
        this.height = height;
        setSize(width, height);
    }

    public CreateCanvasButton(String text, Icon icon, int width, int height) {
        super(text, icon);
        this.text = text;
        this.icon = icon;
        this.width = width;
        this.height = height;
        setSize(width, height);
    }

}
