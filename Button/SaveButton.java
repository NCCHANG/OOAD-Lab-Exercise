package Button;

import javax.swing.*;

public class SaveButton extends JButton {
    private int width;
    private int height;
    private String id;
    private Icon icon;
    private String text;

    public SaveButton(Icon icon, int width, int height) {
        super(icon);
        this.icon = icon;
        this.width = width;
        this.height = height;
        setSize(width, height);
    }

}
