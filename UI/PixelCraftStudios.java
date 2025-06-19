package UI;

import Button.*;
import Canvas.CanvasController;
import Canvas.DrawingCanvas;
import Canvas.ImageCanvas;
import Listener.*;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;

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
    private StrokeSizeListener strokeSizeListener; 
    private SaveButtonListener leftSaveButtonListener;
    private SaveButtonListener rightSaveButtonListener; 


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

        //to control the canvas indirectly
        canvasController = new CanvasController(this);

        //------------------------
        rightCreateCanvasButton = new CreateCanvasButton(new ImageIcon("./icon/create.png"), 30, 30);
        rightCreateCanvasButton.setPreferredSize(new Dimension(30, 30));
        rightCreateCanvasButton.setBorderPainted(false);
        rightCreateCanvasButton.setFocusPainted(false);
        rightCreateCanvasButton.setActionCommand("CREATE_RIGHTCANVAS");
        rightCreateCanvasButton.addActionListener(new CreateCanvasListener(canvasController));
        rightToolbar.add(rightCreateCanvasButton);


        leftCreateCanvasButton = new CreateCanvasButton(new ImageIcon("./icon/create.png"), 30, 30);
        leftCreateCanvasButton.setPreferredSize(new Dimension(30, 30));
        leftCreateCanvasButton.setBorderPainted(false);
        leftCreateCanvasButton.setFocusPainted(false);
        leftCreateCanvasButton.setActionCommand("CREATE_LEFTCANVAS");
        leftCreateCanvasButton.addActionListener(new CreateCanvasListener(canvasController));
        leftToolbar.add(leftCreateCanvasButton);


        leftLibraryCollectionButton = new LibraryCollectionButton(new ImageIcon("./icon/ImageLibrary.png"), 30, 30);
        leftLibraryCollectionButton.setPreferredSize(new Dimension(30, 30));
        leftLibraryCollectionButton.setBorderPainted(false);
        leftLibraryCollectionButton.setFocusPainted(false);
        leftLibraryCollectionButton.setActionCommand("OPEN_LIBRARY_COLLECTION");
        leftLibraryCollectionButton.addActionListener(new LibraryCollectionListener(canvasController));
        leftToolbar.add(leftLibraryCollectionButton);


        leftSaveButton = new SaveButton(new ImageIcon("./icon/save.png"), 30, 30);
        leftSaveButton.setPreferredSize(new Dimension(30, 30));
        leftSaveButton.setBorderPainted(false);
        leftSaveButton.setFocusPainted(false);
        leftSaveButton.setActionCommand("SAVE_BUTTON");
        leftSaveButtonListener = new SaveButtonListener(leftSaveButton);
        leftSaveButton.addActionListener(leftSaveButtonListener);
        leftToolbar.add(leftSaveButton);

        colourSelectionButton = new ColourSelectionButton(new ImageIcon("./icon/color.png"), 30, 30, drawingCanvas);
        colourSelectionButton.setPreferredSize(new Dimension(30, 30));
        colourSelectionButton.setBorderPainted(false);
        colourSelectionButton.setFocusPainted(false);
        colourSelectionButton.setActionCommand("SELECT_COLOR");
        colourSelectionButton.addActionListener(new ColourSelectionListener(colourSelectionButton));
        rightToolbar.add(colourSelectionButton);

        strokeSizeButton = new StrokeSizeButton(new ImageIcon("./icon/stroke.png"), 30, 30);
        strokeSizeButton.setPreferredSize(new Dimension(30, 30));
        strokeSizeButton.setBorderPainted(false);
        strokeSizeButton.setFocusPainted(false);
        strokeSizeButton.setActionCommand("STROKE_BUTTON");
        strokeSizeListener = new StrokeSizeListener(strokeSizeButton);
        strokeSizeButton.addActionListener(strokeSizeListener);
        rightToolbar.add(strokeSizeButton);

        rightSaveButton = new SaveButton(new ImageIcon("./icon/save.png"), 30, 30);
        rightSaveButton.setPreferredSize(new Dimension(30, 30));
        rightSaveButton.setBorderPainted(false);
        rightSaveButton.setFocusPainted(false);
        rightSaveButton.setActionCommand("SAVE_BUTTON");
        rightSaveButtonListener = new SaveButtonListener(rightSaveButton);
        rightSaveButton.addActionListener(rightSaveButtonListener);
        rightToolbar.add(rightSaveButton);

        //------------------------

        //to hold drawing canvas
        leftCanvasPanel = new JPanel(new BorderLayout());
        leftCanvasPanel.setBackground(Color.LIGHT_GRAY);
        //to hold image canvas
        rightCanvasPanel = new JPanel(new BorderLayout());
        rightCanvasPanel.setBackground(Color.LIGHT_GRAY);
        // to split two canvas panels
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftCanvasPanel, rightCanvasPanel);
        splitPane.setDividerLocation(PixelCraftStudiosWidth / 2);

        add(splitPane, BorderLayout.CENTER);

        //-----------------------
        setVisible(true);
    }

    public void setRightDrawingCanvas(DrawingCanvas rightDrawingCanvas, DrawingCanvas drawingCanvas) {
        this.drawingCanvas = drawingCanvas; // Store the reference to the DrawingCanvas
        colourSelectionButton.setDrawingCanvas(drawingCanvas);
        // Set the drawing canvas for the stroke size listener
        if (strokeSizeListener != null) {
            strokeSizeListener.setDrawingCanvas(drawingCanvas);
        }
        // <--- Add this block to set the drawing canvas for the right save button listener
        if (rightSaveButtonListener != null) {
            rightSaveButtonListener.setCanvas(drawingCanvas);
        }
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

        ImageMouseListener imageMouseListener = new ImageMouseListener(imageCanvas);
        leftCanvasPanel.addMouseListener(imageMouseListener);
        leftCanvasPanel.addMouseMotionListener(imageMouseListener);
        leftCanvasPanel.addMouseWheelListener(imageMouseListener);

        leftCreateCanvasButton.setEnabled(false); // Disable button after creating canvas

        leftSaveButtonListener.setCanvas(imageCanvas);

        ImageDropTargetListener dropListener = new ImageDropTargetListener(this.imageCanvas); // Create a new instance of ImageDropTargetListener with the imageCanvas
        new DropTarget(this.leftCanvasPanel, DnDConstants.ACTION_COPY_OR_MOVE, dropListener, true, null); // Enable drag-and-drop functionality on the left canvas panel
    }

    public static void main(String[] args) {
        new PixelCraftStudios();
    }
}
