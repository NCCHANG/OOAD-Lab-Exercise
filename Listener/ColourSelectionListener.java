package Listener;

import Button.ColourSelectionButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColourSelectionListener implements ActionListener {

    private ColourSelectionButton colourSelectionButton;

    public ColourSelectionListener(ColourSelectionButton colourSelectionButton) {
        this.colourSelectionButton = colourSelectionButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("SELECT_COLOR".equals(command)) {
            Color newColor = JColorChooser.showDialog(null, "Choose a Color", colourSelectionButton.getSelectedColor()); //Open a color chooser dialog.
            if (newColor != null) {
                colourSelectionButton.setSelectedColor(newColor); //Set the selected color in the ColourSelectionButton.
            }
        }
    }
}
