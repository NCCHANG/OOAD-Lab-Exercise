package Button;

import javax.swing.*;
import java.awt.*;

public class ColourSelectionButton extends JButton {
    private int width;
    private int height;
    private String id;
    private Icon icon;
    private String text;
    private Color selectedColor;

    public ColourSelectionButton(Icon icon, int width, int height) {
        super(icon);
        this.icon = icon;
        this.width = width;
        this.height = height;
        setSize(width, height);
    }

    public void setSelectedColor(Color color) {
        this.selectedColor = color;
        setBackground(color);
    }

    public Color getSelectedColor() {
        return selectedColor;
    }
}
