package UI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class LoginPage extends UIPage {
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel centerWrapper;
    private JPanel bottomPanel;
    public LoginPage() {
        initUI();
    }

    @Override
    public void initUI() {
        setLayout(new java.awt.BorderLayout());
        topPanel = new JPanel();
        topPanel.setBackground(new Color(191,151,139));
        topPanel.setPreferredSize(new Dimension(1200, 50));
        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        centerPanel.setBackground(new Color(180, 180, 180));
        // tochange: add **event organizer** button and **Student & Staff** login button to centerPanel
        JButton eventOrganizerButton = new JButton("Event Organizer Login");
        JButton studentStaffButton = new JButton("Student & Staff Login");
        centerPanel.add(eventOrganizerButton);
        centerPanel.add(studentStaffButton);
        //wrapper to vertical center centerPanel
        centerWrapper = new JPanel(new java.awt.GridBagLayout());
        centerWrapper.setBackground(new Color(180, 180, 180));
        centerWrapper.add(centerPanel, new java.awt.GridBagConstraints());

        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(113,91,81));
        bottomPanel.setPreferredSize(new Dimension(1200, 50));
        add(topPanel, java.awt.BorderLayout.NORTH);
        add(centerWrapper, java.awt.BorderLayout.CENTER);
        add(bottomPanel, java.awt.BorderLayout.SOUTH);
    }
    
}
