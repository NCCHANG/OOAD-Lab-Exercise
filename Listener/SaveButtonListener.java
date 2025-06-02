package Listener;

import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage; // For creating a BufferedImage from the canvas
import java.io.File; // For file operations
import javax.imageio.ImageIO; // For saving the image
import java.awt.Graphics2D; // For drawing the canvas content onto a BufferedImage
import java.awt.Component; // For JOptionPane parent component

import Button.SaveButton;
import Canvas.DrawingCanvas; // Import DrawingCanvas

public class SaveButtonListener implements ActionListener {

    private SaveButton saveButton;
    private DrawingCanvas drawingCanvas; // Add a reference to DrawingCanvas

    public SaveButtonListener(SaveButton saveButton) {
        this.saveButton = saveButton;
    }

    // Add a setter for DrawingCanvas
    public void setDrawingCanvas(DrawingCanvas drawingCanvas) {
        this.drawingCanvas = drawingCanvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("SAVE_BUTTON".equals(command)) {
            if (drawingCanvas != null) {
                // Show confirmation dialog
                int confirmResult = JOptionPane.showConfirmDialog(
                    (Component) e.getSource(), // Use the button as the parent component for centering
                    "Do you want to save the current drawing?",
                    "Confirm Save",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

                if (confirmResult == JOptionPane.YES_OPTION) {
                    // User confirmed, proceed with saving
                    saveDrawingCanvasContent();
                } else {
                    System.out.println("Save operation cancelled by user.");
                }
            } else {
                JOptionPane.showMessageDialog(
                    null,
                    "Please create a drawing canvas first to save!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }

    private void saveDrawingCanvasContent() {
        if (drawingCanvas == null) {
            JOptionPane.showMessageDialog(null, "No drawing canvas available to save.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create a BufferedImage from the DrawingCanvas content
        BufferedImage image = new BufferedImage(
            drawingCanvas.getWidth(),
            drawingCanvas.getHeight(),
            BufferedImage.TYPE_INT_ARGB // Supports transparency
        );
        Graphics2D g2d = image.createGraphics();
        drawingCanvas.paint(g2d); // Paint the canvas content onto the BufferedImage
        g2d.dispose(); // Release graphics resources

        // Use JFileChooser to get the save location and file name
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Drawing");
        // Set a default file name
        fileChooser.setSelectedFile(new File("my_drawing.png"));

        int userSelection = fileChooser.showSaveDialog(null); // null for parent component

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            // Ensure the file has a .png extension if not provided
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".png")) {
                fileToSave = new File(filePath + ".png");
            }

            try {
                ImageIO.write(image, "PNG", fileToSave);
                JOptionPane.showMessageDialog(null, "Drawing saved successfully to:\n" + fileToSave.getAbsolutePath(), "Save Successful", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Drawing saved to: " + fileToSave.getAbsolutePath());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error saving drawing: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else {
            System.out.println("Save operation cancelled by user via file chooser.");
        }
    }
}
