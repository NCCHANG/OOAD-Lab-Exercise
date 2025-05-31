// PixelCraftStudios.java
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import item.AnimalItem;


import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;


public class PixelCraftStudios extends JFrame {
    private ImageCanvas imageCanvas;
    private CanvasRotationSlider rotationSlider;

    PixelCraftStudios() {
        super("Pixel Craft Studios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

          // Initialize image canvas
        imageCanvas = new ImageCanvas(800, 600, "resource/cat.jpg"); // Make sure the image path is valid
        add(imageCanvas, BorderLayout.CENTER);

   // Create rotation toolbar
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false); // Prevent moving
        rotationSlider = new CanvasRotationSlider(0, 360, 0);
        toolbar.add(new JLabel("Rotation:"));
        toolbar.add(rotationSlider);

        // Wrap toolbar in a panel aligned to bottom-right
        JPanel toolbarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5)); // Align right
        toolbarPanel.add(toolbar);
        add(toolbarPanel, BorderLayout.SOUTH); // Put toolbar panel in the south region

        // 绑定滑块事件
        rotationSlider.addChangeListener(e -> {
            int angle = rotationSlider.getValue();
            imageCanvas.setRotationAngle(angle);
        });

        // Add an AnimalItem to the canvas
        try {
            BufferedImage img = ImageIO.read(new File("resources/cat.png"));
            AnimalItem cat = new AnimalItem(img, 100, 100);
            imageCanvas.addDisplayItem(cat);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new ImageMouseListener(imageCanvas);
    }

    public static void main(String[] args) {
        new PixelCraftStudios();
    }
}