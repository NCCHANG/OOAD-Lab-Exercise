import javax.swing.*;

public class PixelCraftStudios extends JFrame {
    //track left/right canvas existence
    boolean leftCanvas = false;
    boolean rightCanvas = false;
    PixelCraftStudios() {
        super("Pixel Craft Studios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        //------------------------
        JToolBar toolbar = new JToolBar();
        add(toolbar, "North");
        add(new DrawingCanvas(800, 800));
        //-----------------------
        setVisible(true);
    }

    public static void main(String[] args) {
        new PixelCraftStudios();
    }
}