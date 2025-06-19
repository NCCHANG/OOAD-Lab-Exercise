package Button;

import Canvas.DrawingCanvas;

import javax.swing.*;
import java.awt.*;

public class ColourSelectionButton extends JButton {
    private int width;
    private int height;
    private Icon icon;
    private Color selectedColor;
    private DrawingCanvas drawingCanvas;

    public ColourSelectionButton(Icon icon, int width, int height, DrawingCanvas drawingCanvas) {
        super(icon);
        this.icon = icon;
        this.width = width;
        this.height = height;
        this.drawingCanvas = drawingCanvas;
        setSize(width, height);
    }

    public void setSelectedColor(Color color) {
        if (drawingCanvas != null) {
            this.selectedColor = color;
            setBackground(color); // Set the button background to the selected color
            drawingCanvas.setPenColor(color); // Update the drawing canvas with the selected color
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Please create a drawing canvas first!", // Show warning if drawing canvas is not set
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public Color getSelectedColor() {
        return selectedColor; // Return the currently selected color
    }

    public void setDrawingCanvas(DrawingCanvas drawingCanvas) {
        this.drawingCanvas = drawingCanvas;
    }
}
