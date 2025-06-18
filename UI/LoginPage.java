package UI;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class LoginPage extends UIPage {
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    public LoginPage() {
        initUI();
    }

    @Override
    public void initUI() {
        setLayout(new java.awt.BorderLayout());
        topPanel = new JPanel();
        topPanel.setBackground(new Color(191,151,139));
        topPanel.setPreferredSize(new Dimension(1200, 50)); // adjust height as needed
        centerPanel = new JPanel();
        centerPanel.setBackground(new Color(180, 180, 180));
        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(113,91,81));
        bottomPanel.setPreferredSize(new Dimension(1200, 50)); // adjust height as needed
        add(topPanel, java.awt.BorderLayout.NORTH);
        add(centerPanel, java.awt.BorderLayout.CENTER);
        add(bottomPanel, java.awt.BorderLayout.SOUTH);
    }
    
}
