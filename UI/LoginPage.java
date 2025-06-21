// LoginPage.java
package UI;

import Data.CsvEventManager; // Import CsvEventManager (even if not directly used here yet, for consistency)
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent; // Import for ActionListener
import java.awt.event.ActionListener; // Import for ActionListener

import javax.swing.JButton;
import javax.swing.JPanel;

public class LoginPage extends UIPage {
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel centerWrapper;
    private JPanel bottomPanel;

    // Add CsvEventManager field for consistency, even if not used in this page
    private CsvEventManager csvEventManager;

    // Updated constructor to accept both controller and csvEventManager
    public LoginPage(UIController controller, CsvEventManager csvEventManager) {
        super(controller); // Call superclass constructor
        this.csvEventManager = csvEventManager; // Store csvEventManager
        initUI();
    }

    // You can remove the no-arg constructor if UIController no longer calls it
    /*
    public LoginPage() { // Old constructor, likely causing issues if UIController calls it
        initUI();
    }
    */

    @Override
    public void initUI() {
        setLayout(new java.awt.BorderLayout());
        topPanel = new JPanel();
        topPanel.setBackground(new Color(191,151,139));
        topPanel.setPreferredSize(new Dimension(1200, 50));
        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        centerPanel.setBackground(new Color(180, 180, 180));

        // Event Organizer Login Button
        JButton eventOrganizerButton = new JButton("Event Organizer Login");
        eventOrganizerButton.setPreferredSize(new Dimension(200, 50)); // Make button bigger
        eventOrganizerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate to the Event Organizer Functionality page
                controller.showEventOrganizerFunctionalityPage();
            }
        });

        // Student & Staff Login Button
        JButton studentStaffButton = new JButton("Student & Staff Login");
        studentStaffButton.setPreferredSize(new Dimension(200, 50)); // Make button bigger
        studentStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate to the Register/Bill page for students/staff
                // For now, let's just show RegisterPage as an example
                controller.showRegisterPage(); // You can change this later to a proper student/staff dashboard
            }
        });


        centerPanel.add(eventOrganizerButton);
        centerPanel.add(studentStaffButton);

        // Wrapper to vertically center centerPanel
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