// UpdateEventPage.java
package UI;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout; // Import FlowLayout
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Data.CsvEventManager;

import javax.swing.Box;

public class UpdateEventPage extends UIPage {
    private JPanel topPanel;
    private JPanel centerWrapper;
    private JPanel centerPanel;
    private JPanel eventListPanel;
    private JPanel bottomPanel;

    private JLabel eventListLabel;
    private DefaultListModel<String> eventListModel;
    private JScrollPane eventListScroll;
    private JList<String> eventList;
    private JTextField eventInfoField;
    private JButton updateButton;

    // Modified constructor to accept UIController
    private CsvEventManager csvEventManager;
    public UpdateEventPage(UIController controller, CsvEventManager csvEventManager) {
        super(controller);
        this.csvEventManager = csvEventManager;
        initUI();
    }
    @Override
    public void initUI() {
        setLayout(new java.awt.BorderLayout());

        // Top Panel with Return Button
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Set FlowLayout.LEFT
        topPanel.setBackground(new Color(191, 151, 139));
        topPanel.setPreferredSize(new java.awt.Dimension(1200, 50));
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showEventOrganizerFunctionalityPage(); // Go back to the organizer page
            }
        });
        topPanel.add(returnButton); // Add the return button to the top panel


        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(180, 180, 180));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60)); // padding

        // Center wrapper for vertical centering
        centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(new Color(180, 180, 180));
        centerWrapper.add(centerPanel, new java.awt.GridBagConstraints());

        eventListLabel = new JLabel("Event List:");
        eventListLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        eventListModel = new DefaultListModel<>();
        eventList = new JList<>(eventListModel);
        //tochange: add real data
        eventListModel.addElement("Birthday Party");
        eventListModel.addElement("Team Meeting");
        eventListModel.addElement("Conference 2025");
        //------------------------
        eventListScroll = new JScrollPane(eventList);
        eventListScroll.setPreferredSize(new Dimension(600, 180));
        eventListScroll.setAlignmentX(Component.LEFT_ALIGNMENT);

        eventListPanel = new JPanel();
        eventListPanel.setLayout(new BoxLayout(eventListPanel, BoxLayout.Y_AXIS));
        eventListPanel.setBackground(new Color(230, 230, 230));
        eventListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        eventListPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        eventListPanel.add(eventListLabel);
        eventListPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        eventListPanel.add(eventListScroll);

        // Event Info Section (dark blue background)
        JPanel eventInfoPanel = new JPanel();
        eventInfoPanel.setLayout(new BoxLayout(eventInfoPanel, BoxLayout.Y_AXIS));
        eventInfoPanel.setBackground(new Color(80, 76, 112));
        eventInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        eventInfoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel eventInfoLabel = new JLabel("Event Info:");
        eventInfoLabel.setForeground(Color.WHITE);
        eventInfoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel inputPanel = new JPanel(new BorderLayout(10, 0));
        inputPanel.setBackground(new Color(80, 76, 112));

        eventInfoField = new JTextField();
        eventInfoField.setPreferredSize(new Dimension(400, 35));
        eventInfoField.setBackground(new Color(180, 180, 200));

        //tochange: add update button class and listener just update data from dataset
        //          and update the eventListModel
        updateButton = new JButton("Update");
        updateButton.setPreferredSize(new Dimension(80, 35));

        inputPanel.add(eventInfoField, BorderLayout.CENTER);
        inputPanel.add(updateButton, BorderLayout.EAST);

        eventInfoPanel.add(eventInfoLabel);
        eventInfoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        eventInfoPanel.add(inputPanel);

        // add listener when user selects an event from the list
        eventList.addListSelectionListener(e -> {
            String selected = eventList.getSelectedValue();
            eventInfoField.setText(selected != null ? selected : "");
        });

        centerPanel.add(eventListPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(eventInfoPanel);

        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(113, 91, 81));
        bottomPanel.setPreferredSize(new Dimension(1200, 50));

        add(topPanel, BorderLayout.NORTH);
        add(centerWrapper, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}