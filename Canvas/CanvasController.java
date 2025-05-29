package Canvas;
import UI.PixelCraftStudios;

public class CanvasController {
    PixelCraftStudios pixelCraftStudios = null;

    public CanvasController(PixelCraftStudios pixelCraftStudios) {
        this.pixelCraftStudios = pixelCraftStudios;
    }
    
    public void setLeftImageCanvas(ImageCanvas leftImageCanvas) {
    }
    public void setRightDrawingCanvas(DrawingCanvas rightDrawingCanvas) {
        pixelCraftStudios.setRightDrawingCanvas(rightDrawingCanvas);
    }
}
