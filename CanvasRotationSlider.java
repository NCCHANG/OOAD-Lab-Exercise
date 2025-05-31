// CanvasRotationSlider.java
import javax.swing.*;

public class CanvasRotationSlider extends JSlider {
    public CanvasRotationSlider(int min, int max, int value) {
        super(min, max, value);
        setPaintTicks(true);
        setPaintLabels(true);
        setMajorTickSpacing(90); // 每90度一个刻度
        setMinorTickSpacing(30);
    }
}