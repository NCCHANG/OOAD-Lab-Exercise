package Listener;

import Canvas.CanvasController;
import Canvas.ImageCanvas;
import Canvas.ImageItem.AnimalItem;
import Canvas.ImageItem.CustomImageItem;
import Canvas.ImageItem.FlowerItem;
import Canvas.ImageItem.ImageCanvasItem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class LibraryCollectionListener implements ActionListener {
    private CanvasController canvasController;

    public LibraryCollectionListener(CanvasController canvasController) {
        this.canvasController = canvasController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        File defaultFile = new File("./Image/defaultImage.png");
//        JFileChooser fileChooser = new JFileChooser(defaultFile.getParentFile());
//        fileChooser.setSelectedFile(defaultFile);

        // Set default directory to /Image
        JFileChooser fileChooser = new JFileChooser("./Image");
        fileChooser.setDialogTitle("Select an Image");

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(selectedFile);

                if (img != null) {
                    ImageCanvas canvas = canvasController.getImageCanvas();

                    // Scale the image to fit within the canvas
                    BufferedImage scaledImg;
                    if (img.getWidth() > canvas.getWidth() || img.getHeight() > canvas.getHeight()) {
                        double scaleX = (double) canvas.getWidth() / img.getWidth(); // get scale factor for width
                        double scaleY = (double) canvas.getHeight() / img.getHeight(); // get scale factor for height
                        double scale = Math.min(scaleX, scaleY); // use the smaller scale factor to maintain aspect ratio
                        int newWidth = (int) (img.getWidth() * scale); // calculate new width
                        int newHeight = (int) (img.getHeight() * scale);

                        scaledImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

                        scaledImg.createGraphics().drawImage(img, 0, 0, newWidth, newHeight, null);
                    } else {
                        scaledImg = img; // use original image if it fits within the canvas
                    }

                    // Get the center point of the canvas
                    int x = (canvas.getWidth() - scaledImg.getWidth()) / 2;
                    int y = (canvas.getHeight() - scaledImg.getHeight()) / 2;
                    Point centerPoint = new Point(x, y);

                    ImageCanvasItem item;
                    String fileName = selectedFile.getName().toLowerCase();
                    if (fileName.contains("animal")) {
                        item = new AnimalItem(scaledImg, centerPoint);
                    } else if (fileName.contains("flower")) {
                        item = new FlowerItem(scaledImg, centerPoint);
                    } else {
                        item = new CustomImageItem(scaledImg, centerPoint);
                    }

                    canvas.addItem(item);
                } else {
                    JOptionPane.showMessageDialog(null, "Selected file is not a valid image.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Failed to load image: " + ex.getMessage());
            }
        }
    }
}