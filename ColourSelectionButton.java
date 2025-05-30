import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColourSelectionButton extends JButton {

    private Color selectedColor;

    public ColourSelectionButton(String label) {
        super(label);
        selectedColor = Color.BLACK; // default color

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(null, "Choose a Color", selectedColor);
                if (newColor != null) {
                    selectedColor = newColor;
                    setBackground(selectedColor); // update button color for feedback
                }
            }
        });
    }

    public Color getSelectedColor() {
        return selectedColor;
    }
}
