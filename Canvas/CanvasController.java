package Canvas;
import UI.PixelCraftStudios;

public class CanvasController {
    PixelCraftStudios pixelCraftStudios = null;
    private ImageCanvas imageCanvas;

    public CanvasController(PixelCraftStudios pixelCraftStudios) {
        this.pixelCraftStudios = pixelCraftStudios;
    }
    
    public void setLeftImageCanvas(ImageCanvas leftImageCanvas) {
        this.imageCanvas = leftImageCanvas;
        pixelCraftStudios.setLeftImageCanvas(leftImageCanvas);
    }
    public void setRightDrawingCanvas(DrawingCanvas rightDrawingCanvas) {
        pixelCraftStudios.setRightDrawingCanvas(rightDrawingCanvas);
    }

    public ImageCanvas getImageCanvas() {
        return this.imageCanvas;
    }
}
