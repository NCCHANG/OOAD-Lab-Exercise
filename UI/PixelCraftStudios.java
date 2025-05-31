package UI;
import java.awt.*;

import javax.swing.*;

import Button.CreateCanvasButton;
import Button.LibraryCollectionButton;
import Canvas.DrawingCanvas;
import Canvas.ImageCanvas;
import Listener.CreateCanvasListener;
import Listener.SaveButtonListener;
import Listener.LibraryCollectionListener;
import Listener.StrokeSizeListener;
import Canvas.CanvasController;
import Button.ColourSelectionButton;
import Listener.ColourSelectionListener;
import Button.SaveButton;
import Button.StrokeSizeButton;

public class PixelCraftStudios extends JFrame {
    private int PixelCraftStudiosWidth = 800;
    private int PixelCraftStudiosHeight = 600;
    private JToolBar toolbar;
    private JPanel leftToolbar;
    private JPanel rightToolbar;
    private CanvasController canvasController;
    private ImageCanvas imageCanvas;
    private DrawingCanvas drawingCanvas;
    private CreateCanvasButton leftCreateCanvasButton;
    private LibraryCollectionButton leftLibraryCollectionButton;
    private CreateCanvasButton rightCreateCanvasButton;
    private JPanel leftCanvasPanel;
    private JPanel rightCanvasPanel;
    private ColourSelectionButton colourSelectionButton;
    private SaveButton leftSaveButton;
    private SaveButton rightSaveButton;
    private StrokeSizeButton strokeSizeButton;

    JSplitPane splitPane;
    PixelCraftStudios() {
        super("Pixel Craft Studios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(PixelCraftStudiosWidth, PixelCraftStudiosHeight);

        //------------------------
        toolbar = new JToolBar();
        add(toolbar, "North");

        leftToolbar = new JPanel();
        leftToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolbar.add(leftToolbar);
        rightToolbar = new JPanel();
        rightToolbar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolbar.add(rightToolbar);
        //------------------------

        canvasController = new CanvasController(this);

        //------------------------
        rightCreateCanvasButton = new CreateCanvasButton(new ImageIcon("./icon/create.png"), 30, 30);
        rightCreateCanvasButton.setBorderPainted(false);
        rightCreateCanvasButton.setFocusPainted(false);
        rightCreateCanvasButton.setActionCommand("CREATE_RIGHTCANVAS");
        rightCreateCanvasButton.addActionListener(new CreateCanvasListener(canvasController));
        rightToolbar.add(rightCreateCanvasButton);
        

        leftCreateCanvasButton = new CreateCanvasButton(new ImageIcon("./icon/create.png"), 30, 30);
        leftCreateCanvasButton.setBorderPainted(false);
        leftCreateCanvasButton.setFocusPainted(false);
        leftCreateCanvasButton.setActionCommand("CREATE_LEFTCANVAS");
        leftCreateCanvasButton.addActionListener(new CreateCanvasListener(canvasController));
        leftToolbar.add(leftCreateCanvasButton);

        // Create the button
        colourSelectionButton = new ColourSelectionButton(new ImageIcon("./icon/color.png"), 30, 30, drawingCanvas);
        colourSelectionButton.setPreferredSize(new Dimension(30, 30));
        colourSelectionButton.setBorderPainted(false);
        colourSelectionButton.setFocusPainted(false);
        colourSelectionButton.setActionCommand("SELECT_COLOR");
        colourSelectionButton.addActionListener(new ColourSelectionListener(colourSelectionButton));
        leftToolbar.add(colourSelectionButton);

        leftSaveButton = new SaveButton(new ImageIcon("./icon/save.png"), 30, 30);
        leftSaveButton.setPreferredSize(new Dimension(30, 30));
        leftSaveButton.setBorderPainted(false);
        leftSaveButton.setFocusPainted(false);
        leftSaveButton.setActionCommand("SAVE_BUTTON");
        leftSaveButton.addActionListener(new SaveButtonListener(leftSaveButton));
        leftToolbar.add(leftSaveButton);

        leftLibraryCollectionButton = new LibraryCollectionButton(new ImageIcon("./icon/ImageLibrary.png"), 30, 30);
        leftLibraryCollectionButton.setBorderPainted(false);
        leftLibraryCollectionButton.setFocusPainted(false);
        leftLibraryCollectionButton.setActionCommand("OPEN_LIBRARY_COLLECTION");
        leftLibraryCollectionButton.addActionListener(new LibraryCollectionListener(canvasController));
        leftToolbar.add(leftLibraryCollectionButton);

        rightSaveButton = new SaveButton(new ImageIcon("./icon/save.png"), 30, 30);
        rightSaveButton.setPreferredSize(new Dimension(30, 30));
        rightSaveButton.setBorderPainted(false);
        rightSaveButton.setFocusPainted(false);
        rightSaveButton.setActionCommand("SAVE_BUTTON");
        rightSaveButton.addActionListener(new SaveButtonListener(rightSaveButton));
        rightToolbar.add(rightSaveButton);

        strokeSizeButton = new StrokeSizeButton(new ImageIcon("./icon/stroke.png"), 30, 30);
        strokeSizeButton.setPreferredSize(new Dimension(30, 30));
        strokeSizeButton.setBorderPainted(false);
        strokeSizeButton.setFocusPainted(false);
        strokeSizeButton.setActionCommand("STROKE_BUTTON");
        strokeSizeButton.addActionListener(new StrokeSizeListener(strokeSizeButton));
        rightToolbar.add(strokeSizeButton);

        JSlider rotationSlider = new JSlider(JSlider.HORIZONTAL, 0, 360, 0); // 0 to 360 degrees
        rotationSlider.setMajorTickSpacing(90);
        rotationSlider.setMinorTickSpacing(15);
        rotationSlider.setPaintTicks(true);
        rotationSlider.setPaintLabels(true);
        rightToolbar.add(rotationSlider);

        rotationSlider.addChangeListener(e -> {
            int angle = rotationSlider.getValue();
            imageCanvas.setRotationAngle(angle);
        });
        //------------------------

        leftCanvasPanel = new JPanel(new BorderLayout());
        leftCanvasPanel.setBackground(Color.LIGHT_GRAY);
        
        rightCanvasPanel = new JPanel(new BorderLayout());
        rightCanvasPanel.setBackground(Color.LIGHT_GRAY);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftCanvasPanel, rightCanvasPanel);
        splitPane.setDividerLocation(PixelCraftStudiosWidth / 2);

        add(splitPane, BorderLayout.CENTER);

        //-----------------------
        setVisible(true);
    }

    public void setRightDrawingCanvas(DrawingCanvas rightDrawingCanvas, DrawingCanvas drawingCanvas) {
        this.drawingCanvas = drawingCanvas; // Store the reference to the DrawingCanvas
        colourSelectionButton.setDrawingCanvas(drawingCanvas);
        rightCanvasPanel.add(rightDrawingCanvas, BorderLayout.CENTER);
        rightCanvasPanel.revalidate();
        rightCanvasPanel.repaint();
        rightCreateCanvasButton.setEnabled(false); // Disable button after creating canvas
    }

    public void setLeftImageCanvas(JPanel leftImageCanvas, ImageCanvas imageCanvas) {
        this.imageCanvas = imageCanvas; // Store the reference to the ImageCanvas
        leftCanvasPanel.add(leftImageCanvas, BorderLayout.CENTER);
        leftCanvasPanel.revalidate();
        leftCanvasPanel.repaint();
        leftCreateCanvasButton.setEnabled(false); // Disable button after creating canvas
    }

    public static void main(String[] args) {
        new PixelCraftStudios();
    }
}