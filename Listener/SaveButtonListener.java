package Listener;

import java.awt.event.*;


public class SaveButtonListener implements ActionListener {

    private Button.SaveButton saveButton;

    public SaveButtonListener(Button.SaveButton saveButton) {
        this.saveButton = saveButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implement the save functionality here
        // For example, you might want to save the current canvas state to a file
        String command = e.getActionCommand();

        if ("SAVE_BUTTON".equals(command)) {
            System.out.println("Save button clicked. Implement save functionality here.");

            }
        }
}
