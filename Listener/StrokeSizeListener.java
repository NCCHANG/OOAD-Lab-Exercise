package Listener;

import java.awt.event.*;

public class StrokeSizeListener implements ActionListener {

    private Button.StrokeSizeButton strokeSizeButton;

    public StrokeSizeListener(Button.StrokeSizeButton strokeSizeButton) {
        this.strokeSizeButton = strokeSizeButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implement the save functionality here
        // For example, you might want to save the current canvas state to a file
        String command = e.getActionCommand();

        if ("STROKE_BUTTON".equals(command)) {
            System.out.println("STROKE_BUTTON clicked. Implement save functionality here.");

            }
        }
}
