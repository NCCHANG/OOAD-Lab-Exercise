import javax.swing.*;

import Canvas.CreateCanvasButton;
import Canvas.DrawingCanvas;

public class PixelCraftStudios extends JFrame {
    PixelCraftStudios() {
        super("Pixel Craft Studios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        //------------------------
        JToolBar toolbar = new JToolBar();
        add(toolbar, "North");

        CreateCanvasButton createCanvasButton = new CreateCanvasButton(new ImageIcon("./icon/create.png"), 30, 30);
        createCanvasButton.setBorderPainted(false);
        createCanvasButton.setFocusPainted(false);
        createCanvasButton.addActionListener(new Canvas.CreateCanvasListener());
        createCanvasButton.setId("createDrawingCanvas");
        toolbar.add(createCanvasButton);

        //-----------------------
        setVisible(true);
    }

    public static void main(String[] args) {
        new PixelCraftStudios();
    }
}