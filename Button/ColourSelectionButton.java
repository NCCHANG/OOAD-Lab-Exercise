package Button;

import javax.swing.*;

import Canvas.DrawingCanvas;

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
        if(drawingCanvas != null) {
            this.selectedColor = color;
            setBackground(color);
            drawingCanvas.setPenColor(color);
        }else {
            JOptionPane.showMessageDialog(
                null,
                "Please create a drawing canvas first!",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void setDrawingCanvas(DrawingCanvas drawingCanvas) {
        this.drawingCanvas = drawingCanvas;
    }
}
