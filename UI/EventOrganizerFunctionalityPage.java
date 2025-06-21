// EventOrganizerFunctionalityPage.java
package UI;

import Data.CsvEventManager; // Import CsvEventManager (for consistency)
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension; // Import Dimension
import java.awt.event.ActionEvent; // Import for ActionListener
import java.awt.event.ActionListener; // Import for ActionListener

import javax.swing.JButton;
import javax.swing.JPanel;

public class EventOrganizerFunctionalityPage extends UIPage {
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel centerWrapper;
    private JPanel bottomPanel;

    // Add CsvEventManager field for consistency
    private CsvEventManager csvEventManager;

    // Updated constructor to accept both controller and csvEventManager
    public EventOrganizerFunctionalityPage(UIController controller, CsvEventManager csvEventManager) {
        super(controller); // Call superclass constructor
        this.csvEventManager = csvEventManager; // Store csvEventManager
        initUI();
    }

    // You can remove the no-arg constructor if UIController no longer calls it
    /*
    public EventOrganizerFunctionalityPage() { // Old constructor, likely causing issues
        initUI();
    }
    */

    @Override
    public void initUI() {
        setLayout(new BorderLayout());
        topPanel = new JPanel();
        topPanel.setBackground(new Color(191, 151, 139));
        topPanel.setPreferredSize(new Dimension(1200, 50));

        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        centerPanel.setBackground(new Color(180, 180, 180));

        // Create Event Button
        JButton createEventButton = new JButton("Create Event");
        createEventButton.setPreferredSize(new Dimension(150, 40)); // Consistent button size
        createEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showCreateEventPage(); // Navigate to Create Event page
            }
        });

        // Cancel Event Button
        JButton cancelEventButton = new JButton("Delete Event"); // Renamed for clarity
        cancelEventButton.setPreferredSize(new Dimension(150, 40)); // Consistent button size
        cancelEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showDeleteEventPage(); // Navigate to Delete Event page
            }
        });

        // Update Event Button
        JButton updateEventButton = new JButton("Update Event");
        updateEventButton.setPreferredSize(new Dimension(150, 40)); // Consistent button size
        updateEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showUpdateEventPage(); // Navigate to Update Event page
            }
        });

        centerPanel.add(createEventButton);
        centerPanel.add(cancelEventButton);
        centerPanel.add(updateEventButton);

        // Wrapper to vertically center centerPanel
        centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(new Color(180, 180, 180));
        centerWrapper.add(centerPanel, new GridBagConstraints());

        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(113, 91, 81));
        bottomPanel.setPreferredSize(new Dimension(1200, 50));

        add(topPanel, BorderLayout.NORTH);
        add(centerWrapper, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}