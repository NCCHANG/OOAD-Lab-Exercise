package UI;

public class EventOrganizerFunctionalityPage extends UIPage {
    private javax.swing.JPanel topPanel;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JPanel centerWrapper;
    private javax.swing.JPanel bottomPanel;

    public EventOrganizerFunctionalityPage() {
        initUI();
    }

    @Override
    public void initUI() {
        setLayout(new java.awt.BorderLayout());
        topPanel = new javax.swing.JPanel();
        topPanel.setBackground(new java.awt.Color(191, 151, 139));
        topPanel.setPreferredSize(new java.awt.Dimension(1200, 50));

        centerPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 60, 0));
        centerPanel.setBackground(new java.awt.Color(180, 180, 180));

        // tochange: add **createEventButton** button and **cancelEventButton** and **updateEventButton** button to centerPanel
        javax.swing.JButton createEventButton = new javax.swing.JButton("Create Event");
        javax.swing.JButton cancelEventButton = new javax.swing.JButton("Cancel Event");
        javax.swing.JButton updateEventButton = new javax.swing.JButton("Update Event");
        centerPanel.add(createEventButton);
        centerPanel.add(cancelEventButton);
        centerPanel.add(updateEventButton);

        // Wrapper to vertically center centerPanel
        centerWrapper = new javax.swing.JPanel(new java.awt.GridBagLayout());
        centerWrapper.setBackground(new java.awt.Color(180, 180, 180));
        centerWrapper.add(centerPanel, new java.awt.GridBagConstraints());

        bottomPanel = new javax.swing.JPanel();
        bottomPanel.setBackground(new java.awt.Color(113, 91, 81));
        bottomPanel.setPreferredSize(new java.awt.Dimension(1200, 50));

        add(topPanel, java.awt.BorderLayout.NORTH);
        add(centerWrapper, java.awt.BorderLayout.CENTER);
        add(bottomPanel, java.awt.BorderLayout.SOUTH);
    }
    
}
