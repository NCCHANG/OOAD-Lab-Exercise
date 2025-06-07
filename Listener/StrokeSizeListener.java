package Listener;

import Button.StrokeSizeButton;
import Canvas.DrawingCanvas;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StrokeSizeListener implements ActionListener {

    private StrokeSizeButton strokeSizeButton;
    private DrawingCanvas drawingCanvas; // Add a reference to DrawingCanvas

    // Modify the constructor to accept DrawingCanvas
    public StrokeSizeListener(StrokeSizeButton strokeSizeButton) { // Keep this constructor for initial setup
        this.strokeSizeButton = strokeSizeButton;
        // drawingCanvas will be null initially, set via setDrawingCanvas later
    }

    // Add a setter for DrawingCanvas
    public void setDrawingCanvas(DrawingCanvas drawingCanvas) {
        this.drawingCanvas = drawingCanvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("STROKE_BUTTON".equals(command)) {
            if (drawingCanvas != null) {
                // Create a new dialog for the slider
                JDialog sliderDialog = new JDialog();
                sliderDialog.setTitle("Adjust Pen Stroke Size");
                sliderDialog.setModal(true); // Make it modal so user has to interact with it
                sliderDialog.setSize(300, 150);
                sliderDialog.setLocationRelativeTo(null); // Center on screen

                int initialStroke = (drawingCanvas != null) ? drawingCanvas.getPenStroke() : 4; // Use getter
                JSlider strokeSlider = new JSlider(JSlider.HORIZONTAL, 1, 20, initialStroke); // Min 1, Max 20, current penStroke
                strokeSlider.setMajorTickSpacing(5);
                strokeSlider.setMinorTickSpacing(1);
                strokeSlider.setPaintTicks(true);
                strokeSlider.setPaintLabels(true);

                // Add a ChangeListener to the slider
                strokeSlider.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent ce) {
                        JSlider source = (JSlider) ce.getSource();
                        // Update the stroke size immediately as the slider is dragged
                        int newStrokeSize = (int) source.getValue();
                        if (drawingCanvas != null) {
                            drawingCanvas.setPenStroke(newStrokeSize);
                            // No need to repaint here, as the drawing canvas will repaint on next drag/draw
                            // System.out.println("Pen stroke size set to: " + newStrokeSize);
                        }
                    }
                });

                // Add components to the dialog
                JPanel panel = new JPanel(new BorderLayout());
                panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                panel.add(strokeSlider, BorderLayout.CENTER);

                // Add an OK button to close the dialog
                JButton okButton = new JButton("OK");
                okButton.addActionListener(event -> sliderDialog.dispose()); // Close dialog on OK
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.add(okButton);
                panel.add(buttonPanel, BorderLayout.SOUTH);


                sliderDialog.add(panel);
                sliderDialog.setVisible(true); // Show the dialog

            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Please create a drawing canvas first!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }
}
