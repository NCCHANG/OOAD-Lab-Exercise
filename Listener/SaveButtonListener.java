package Listener;

import Button.SaveButton;
import Canvas.Canvas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class SaveButtonListener implements ActionListener {

    private SaveButton saveButton;
    private Canvas canvas; // Add a reference to DrawingCanvas

    public SaveButtonListener(SaveButton saveButton) {
        this.saveButton = saveButton;
    }

    // Add a setter for DrawingCanvas
    public void setCanvas(Canvas drawingCanvas) {
        this.canvas = drawingCanvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("SAVE_BUTTON".equals(command)) {
            if (canvas != null) {
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
        if (canvas == null) {
            JOptionPane.showMessageDialog(null, "No drawing canvas available to save.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create a BufferedImage from the DrawingCanvas content
        BufferedImage image = new BufferedImage(
                canvas.getWidth(),
                canvas.getHeight(),
                BufferedImage.TYPE_INT_ARGB // Supports transparency
        );
        Graphics2D g2d = image.createGraphics();
        canvas.paint(g2d); // Paint the canvas content onto the BufferedImage
        g2d.dispose(); // Release graphics resources

        // Use JFileChooser to get the save location and file name
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Drawing");
        // Set a default file name
        fileChooser.setSelectedFile(new File("my_drawing.png"));

        // set default directory to custom path
        fileChooser.setCurrentDirectory(new File("./Image"));

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
