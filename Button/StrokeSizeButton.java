package Button;

import javax.swing.*;

public class StrokeSizeButton extends JButton {
    private int width;
    private int height;
    private Icon icon;

    public StrokeSizeButton(Icon icon, int width, int height) {
        super(icon);
        this.icon = icon;
        this.width = width;
        this.height = height;
    }
}
