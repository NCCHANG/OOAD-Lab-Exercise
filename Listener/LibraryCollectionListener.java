package Listener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Canvas.CanvasController;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.Point;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import Canvas.ImageItem;
import Canvas.ImageCanvas;

public class LibraryCollectionListener implements ActionListener {
    private CanvasController canvasController;

    public LibraryCollectionListener(CanvasController canvasController) {
        this.canvasController = canvasController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(selectedFile);
                if (img != null) {
                    ImageCanvas canvas = canvasController.getImageCanvas();
                    ImageItem item = new ImageItem(img, new Point(50, 50));
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