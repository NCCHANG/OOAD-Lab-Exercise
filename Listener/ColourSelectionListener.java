package Listener;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Button.ColourSelectionButton;

public class ColourSelectionListener implements ActionListener {

    private ColourSelectionButton colourSelectionButton;

    public ColourSelectionListener(ColourSelectionButton colourSelectionButton) {
        this.colourSelectionButton = colourSelectionButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("SELECT_COLOR".equals(command)) {
            Color newColor = JColorChooser.showDialog(null, "Choose a Color", colourSelectionButton.getSelectedColor());
            if (newColor != null) {
                colourSelectionButton.setSelectedColor(newColor);
            }
        }
    }
}
