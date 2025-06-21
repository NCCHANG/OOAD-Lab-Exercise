package Listener;

import Canvas.ImageCanvas;
import Canvas.ImageItem.AnimalItem;
import Canvas.ImageItem.CustomImageItem;
import Canvas.ImageItem.FlowerItem;
import Canvas.ImageItem.ImageCanvasItem;

import javax.imageio.ImageIO; // for reading images
import javax.swing.*; // for GUI components
import java.awt.*; // for Point class
import java.awt.datatransfer.DataFlavor; // for data transfer
import java.awt.datatransfer.Transferable; // for transferable data
import java.awt.datatransfer.UnsupportedFlavorException; // for unsupported flavor exceptions
import java.awt.dnd.*; // for drag and drop functionality
import java.awt.image.BufferedImage; // for image manipulation
import java.io.File; // for file handling
import java.io.IOException; // for IO exceptions
import java.util.List; // for list handling

public class ImageDropTargetListener implements DropTargetListener {

    private ImageCanvas imageCanvas;

    public ImageDropTargetListener(ImageCanvas imageCanvas) {
        this.imageCanvas = imageCanvas;
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            dtde.acceptDrag(DnDConstants.ACTION_COPY);
        } else {
            dtde.rejectDrag();
        }
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        if (!dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            dtde.rejectDrop();
            return;
        }

        dtde.acceptDrop(DnDConstants.ACTION_COPY);
        Transferable transferable = dtde.getTransferable();
        boolean anyImageProcessed = false;

        try {
            List<File> files = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
            if (files != null && !files.isEmpty()) {
                for (File file : files) {
                    System.out.println("Dropped file: " + file.getAbsolutePath());
                    if (isImageFile(file)) {
                        BufferedImage img = ImageIO.read(file);
                        if (img != null && imageCanvas != null) {
                            // Logic adapted from LibraryCollectionListener
                            BufferedImage scaledImg;
                            if (imageCanvas.getWidth() <= 0 || imageCanvas.getHeight() <= 0) {
                                scaledImg = img;
                                JOptionPane.showMessageDialog(null, "Canvas size is invalid for " + file.getName() + ", image may not display correctly.", "Warning", JOptionPane.WARNING_MESSAGE);
                            } else if (img.getWidth() > imageCanvas.getWidth() || img.getHeight() > imageCanvas.getHeight()) {
                                double scaleX = (double) imageCanvas.getWidth() / img.getWidth();
                                double scaleY = (double) imageCanvas.getHeight() / img.getHeight();
                                double scale = Math.min(scaleX, scaleY);
                                int newWidth = (int) (img.getWidth() * scale);
                                int newHeight = (int) (img.getHeight() * scale);

                                newWidth = Math.max(1, newWidth);
                                newHeight = Math.max(1, newHeight);

                                scaledImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
                                scaledImg.createGraphics().drawImage(img, 0, 0, newWidth, newHeight, null);
                            } else {
                                scaledImg = img;
                            }

                            int x = (imageCanvas.getWidth() - scaledImg.getWidth()) / 2;
                            int y = (imageCanvas.getHeight() - scaledImg.getHeight()) / 2;
                            Point centerPoint = new Point(x, y);

                            ImageCanvasItem item;
                            String fileName = file.getName().toLowerCase();
                            if (fileName.contains("animal")) {
                                item = new AnimalItem(scaledImg, centerPoint);
                            } else if (fileName.contains("flower")) {
                                item = new FlowerItem(scaledImg, centerPoint);
                            } else {
                                item = new CustomImageItem(scaledImg, centerPoint);
                            }

                            imageCanvas.addItem(item);
                            anyImageProcessed = true; // Mark that at least one image was processed
                        }
                    }
                }

                if (anyImageProcessed) {
                    dtde.dropComplete(true);
                } else {
                    // If loop completes, and no valid image was processed from the dropped files
                    JOptionPane.showMessageDialog(null, "No valid image files found in drop.", "Drop Info", JOptionPane.INFORMATION_MESSAGE);
                    dtde.dropComplete(false);
                }
            } else {
                // No files in the list or list is null
                dtde.dropComplete(false);
            }
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error processing dropped file(s): " + e.getMessage(), "Drop Error", JOptionPane.ERROR_MESSAGE);
            dtde.dropComplete(false);
        } catch (Exception ex) { // Catch other potential exceptions
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to add dropped image(s): " + ex.getMessage(), "Drop Error", JOptionPane.ERROR_MESSAGE);
            dtde.dropComplete(false);
        }
    }

    private boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".gif");
    }
}