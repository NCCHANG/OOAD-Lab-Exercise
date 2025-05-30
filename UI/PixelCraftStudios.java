package UI;
import java.awt.*;

import javax.swing.*;

import Button.CreateCanvasButton;
import Canvas.ImageCanvas;
import Canvas.DrawingCanvas;
import Listener.CreateCanvasListener;
import Canvas.CanvasController;
import Button.ColourSelectionButton;
import Listener.ColourSelectionListener;

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
    private CreateCanvasButton rightCreateCanvasButton;
    private JPanel leftCanvasPanel;
    private JPanel rightCanvasPanel;
    private ColourSelectionButton colourSelectionButton;
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
        rightToolbar.add(colourSelectionButton);
        //------------------------

        leftCanvasPanel = new JPanel(new BorderLayout());
        leftCanvasPanel.setBackground(Color.WHITE);
        
        rightCanvasPanel = new JPanel(new BorderLayout());
        rightCanvasPanel.setBackground(Color.LIGHT_GRAY);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftCanvasPanel, rightCanvasPanel);
        splitPane.setDividerLocation(PixelCraftStudiosWidth / 2);

        add(splitPane, BorderLayout.CENTER);

        //-----------------------
        setVisible(true);
    }

    public void setRightDrawingCanvas(DrawingCanvas rightDrawingCanvas) {
        drawingCanvas = rightDrawingCanvas;
        colourSelectionButton.setDrawingCanvas(drawingCanvas);
        rightCanvasPanel.add(rightDrawingCanvas, BorderLayout.CENTER);
        rightCanvasPanel.revalidate();
        rightCanvasPanel.repaint();
        rightCreateCanvasButton.setEnabled(false); // Disable button after creating canvas
    }

    public void setLeftImageCanvas(JPanel leftImageCanvas) {
        leftCanvasPanel.add(leftImageCanvas, BorderLayout.CENTER);
        leftCanvasPanel.revalidate();
        leftCanvasPanel.repaint();
        leftCreateCanvasButton.setEnabled(false); // Disable button after creating canvas
    }

    public static void main(String[] args) {
        new PixelCraftStudios();
    }
}