// BillPage.java
package UI;

import java.awt.*;
import javax.swing.*;

import Data.CsvEventManager;

public class BillPage extends UIPage {
    private JPanel topPanel;
    private JPanel centerWrapper;
    private JPanel centerPanel;
    private JPanel eventListPanel;
    private JPanel bottomPanel;

    private JLabel eventListLabel;
    private DefaultListModel<String> eventListModel;
    private JScrollPane eventListScroll;
    private JList<String> eventList;
    private JButton payButton;
    private CsvEventManager csvEventManager;
    // Modified constructor to accept UIController

    public BillPage(UIController controller, CsvEventManager csvEventManager) {
        super(controller);
        this.csvEventManager = csvEventManager;
        initUI();
    }

    @Override
    public void initUI() {
        setLayout(new java.awt.BorderLayout());
        topPanel = new JPanel();
        topPanel.setBackground(new Color(191, 151, 139));
        topPanel.setPreferredSize(new java.awt.Dimension(1200, 50));

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(180, 180, 180));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60)); // padding

        // Center wrapper for vertical centering
        centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(new Color(180, 180, 180));
        centerWrapper.add(centerPanel, new java.awt.GridBagConstraints());

        eventListLabel = new JLabel("Payment Summary:");
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

        //tochange: add pay button class and listener
        payButton = new JButton("Pay");
        payButton.setPreferredSize(new Dimension(90, 35));

        centerPanel.add(eventListPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(payButton);

        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(113, 91, 81));
        bottomPanel.setPreferredSize(new Dimension(1200, 50));

        add(topPanel, BorderLayout.NORTH);
        add(centerWrapper, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}