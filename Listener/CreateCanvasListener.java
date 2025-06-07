package Listener;

import Canvas.CanvasController;
import Canvas.DrawingCanvas;
import Canvas.ImageCanvas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCanvasListener implements ActionListener {

    private CanvasController canvasController;

    public CreateCanvasListener(CanvasController canvasController) {
        this.canvasController = canvasController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        // Show input dialogs for width and height
        String widthCanvas = JOptionPane.showInputDialog("Enter canvas width:");
        String heightCanvas = JOptionPane.showInputDialog("Enter canvas height:");
        if (widthCanvas == null || heightCanvas == null) return; // User cancelled

        try {
            int width = Integer.parseInt(widthCanvas);
            int height = Integer.parseInt(heightCanvas);

            if ("CREATE_LEFTCANVAS".equals(command)) {
                ImageCanvas leftImageCanvas = new ImageCanvas(width, height);
                canvasController.setLeftImageCanvas(leftImageCanvas);
                leftImageCanvas.repaint();
            } else if ("CREATE_RIGHTCANVAS".equals(command)) {
                DrawingCanvas rightDrawingCanvas = new DrawingCanvas(width, height);
                canvasController.setRightDrawingCanvas(rightDrawingCanvas);
                rightDrawingCanvas.repaint();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers for width and height.");
        }
    }
}
