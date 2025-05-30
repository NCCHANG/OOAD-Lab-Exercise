package Button;

import javax.swing.*;
import java.awt.*;


public class StrokeSizeButton extends JButton {
    private int strokeSize;
    private int width;
    private int height;
    private String id;
    private Icon icon;
    private String text;
    private Color selectedColor;


    public StrokeSizeButton(Icon icon, int width, int height) {
        super(icon);
        this.icon = icon;
        this.width = width;
        this.height = height;
        this.strokeSize = strokeSize;
    }

    public int getStrokeSize() {
        return strokeSize;
    }

    public void setStrokeSize(int strokeSize) {
        this.strokeSize = strokeSize;
        setText(String.valueOf(strokeSize));
    }

}
