package Canvas;
import javax.swing.*;

import java.awt.*;

public class ImageCanvas extends Canvas {
    
    public ImageCanvas(int width, int height) {
        super(width, height);
        setBackground(Color.white);
        setVisible(true);
    }
    protected void paintComponent(Graphics g) {
    }
}
