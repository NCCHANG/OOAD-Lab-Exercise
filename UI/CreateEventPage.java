// CreateEventPage.java
package UI;

import Data.CsvEventManager;
import Model.Event;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate; // Still needed for LocalDate.now() and parsing
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel; 
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox; 
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane; 
import javax.swing.JTextField; // Back to JTextField
import javax.swing.Box;
import javax.swing.JTextArea;

// Removed import com.toedter.calendar.JDateChooser;
// Removed import java.util.Date;
// Removed import java.time.ZoneId;

public class CreateEventPage extends UIPage {
    private JPanel topPanel;
    private JPanel centerWrapper;
    private JPanel centerPanel;
    private JPanel eventListPanel;
    private JPanel bottomPanel;

    private JLabel eventListLabel;
    private DefaultComboBoxModel<Event> eventComboBoxModel;
    private JComboBox<Event> eventComboBox;

    private JTextField eventNameField;
    private JTextArea eventDescriptionArea;
    private JTextField eventDateField; // Reverted to JTextField
    private JTextField organizerNameField;
    private JTextField venueField;
    private JTextField capacityField;
    private JTextField registrationFeeField;

    private JButton addButton;

    private CsvEventManager csvEventManager;

    public CreateEventPage(UIController controller, CsvEventManager csvEventManager) {
        super(controller);
        this.csvEventManager = csvEventManager;
        initUI();
    }

    @Override
    public void initUI() {
        setLayout(new java.awt.BorderLayout());

        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(191, 151, 139));
        topPanel.setPreferredSize(new java.awt.Dimension(1200, 50));
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showEventOrganizerFunctionalityPage();
            }
        });
        topPanel.add(returnButton);

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(180, 180, 180));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(new Color(180, 180, 180));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        centerWrapper.add(centerPanel, gbc);


        // --- Event List Section (Top Part of Center Panel) ---
        eventListLabel = new JLabel("Existing Events:");
        eventListLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        eventComboBoxModel = new DefaultComboBoxModel<>();
        eventComboBox = new JComboBox<>(eventComboBoxModel);
        eventComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        eventComboBox.setPreferredSize(new Dimension(800, 30));
        eventComboBox.setMaximumSize(new Dimension(800, 30));


        eventListPanel = new JPanel();
        eventListPanel.setLayout(new BoxLayout(eventListPanel, BoxLayout.Y_AXIS));
        eventListPanel.setBackground(new Color(230, 230, 230));
        eventListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        eventListPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        eventListPanel.add(eventListLabel);
        eventListPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        eventListPanel.add(eventComboBox);
        
        eventListPanel.setMaximumSize(new Dimension(800, Integer.MAX_VALUE)); 


        // --- Event Info Input Section (Bottom Part of Center Panel) ---
        JPanel eventInputPanel = new JPanel();
        eventInputPanel.setLayout(new BoxLayout(eventInputPanel, BoxLayout.Y_AXIS));
        eventInputPanel.setBackground(new Color(80, 76, 112));
        eventInputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        eventInputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        eventInputPanel.setMaximumSize(new Dimension(800, Integer.MAX_VALUE)); 


        JLabel createEventLabel = new JLabel("Create New Event:");
        createEventLabel.setForeground(Color.WHITE);
        createEventLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        eventInputPanel.add(createEventLabel);
        eventInputPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // --- Add input fields using the helper method ---
        eventNameField = new JTextField(20);
        eventInputPanel.add(createInputRow("Event Name:", eventNameField));
        eventInputPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Description uses JTextArea in a JScrollPane
        JPanel descriptionPanel = new JPanel(new BorderLayout(5, 0));
        descriptionPanel.setBackground(new Color(80, 76, 112));
        JLabel descLabel = new JLabel("Description:");
        descLabel.setForeground(Color.WHITE);
        descLabel.setPreferredSize(new Dimension(120, 25));
        
        eventDescriptionArea = new JTextArea(3, 20);
        eventDescriptionArea.setLineWrap(true);
        eventDescriptionArea.setWrapStyleWord(true);
        eventDescriptionArea.setMinimumSize(new Dimension(50, 50));
        eventDescriptionArea.setPreferredSize(new Dimension(50, 60));
        eventDescriptionArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80)); 
        
        JScrollPane descScrollPane = new JScrollPane(eventDescriptionArea);
        descScrollPane.setMinimumSize(new Dimension(50, 60));
        descScrollPane.setPreferredSize(new Dimension(50, 70));
        descScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90)); 

        descriptionPanel.add(descLabel, BorderLayout.WEST);
        descriptionPanel.add(descScrollPane, BorderLayout.CENTER);

        descriptionPanel.setMinimumSize(new Dimension(100, 70));
        descriptionPanel.setPreferredSize(new Dimension(500, 70)); 
        descriptionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70)); 
        descriptionPanel.setAlignmentX(Component.LEFT_ALIGNMENT); 
        eventInputPanel.add(descriptionPanel);
        eventInputPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Initializing eventDateField with current date
        eventDateField = new JTextField(LocalDate.now().toString(), 10); // Set current date as default
        eventInputPanel.add(createInputRow("Event Date:", eventDateField));
        eventInputPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        organizerNameField = new JTextField(20);
        eventInputPanel.add(createInputRow("Organizer Name:", organizerNameField));
        eventInputPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        venueField = new JTextField(20);
        eventInputPanel.add(createInputRow("Venue:", venueField));
        eventInputPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        capacityField = new JTextField(10);
        eventInputPanel.add(createInputRow("Capacity:", capacityField));
        eventInputPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        registrationFeeField = new JTextField(10);
        eventInputPanel.add(createInputRow("Reg. Fee (RM):", registrationFeeField));
        eventInputPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Add Button
        addButton = new JButton("Add Event");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(80, 76, 112));
        buttonPanel.add(addButton);
        eventInputPanel.add(buttonPanel);

        eventInputPanel.add(Box.createVerticalGlue());


        centerPanel.add(eventListPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
        centerPanel.add(eventInputPanel);
        centerPanel.add(Box.createVerticalGlue());


        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(113, 91, 81));
        bottomPanel.setPreferredSize(new Dimension(1200, 50));

        add(topPanel, BorderLayout.NORTH);
        add(centerWrapper, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        refreshEventList(); 

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewEvent();
            }
        });
    }

    // --- Helper method to create input rows ---
    // (Only one version needed now since we're back to JTextField)
    private JPanel createInputRow(String labelText, JTextField textField) {
        JPanel rowPanel = new JPanel(new BorderLayout(5, 0));
        rowPanel.setBackground(new Color(80, 76, 112));
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(120, 25));
        rowPanel.add(label, BorderLayout.WEST);

        textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 35));
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        rowPanel.add(textField, BorderLayout.CENTER);
        rowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        rowPanel.setMinimumSize(new Dimension(100, 30));
        rowPanel.setPreferredSize(new Dimension(500, 30)); 
        rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        return rowPanel;
    }

    private void addNewEvent() {
        String eventName = eventNameField.getText().trim();
        String eventDescription = eventDescriptionArea.getText().trim();
        String eventDateStr = eventDateField.getText().trim(); // Get string directly from JTextField
        String organizerName = organizerNameField.getText().trim();
        String venue = venueField.getText().trim();
        String capacityStr = capacityField.getText().trim();
        String registrationFeeStr = registrationFeeField.getText().trim();

        if (eventName.isEmpty() || eventDateStr.isEmpty() || organizerName.isEmpty() ||
            venue.isEmpty() || capacityStr.isEmpty() || registrationFeeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate eventDate;
        try {
            eventDate = LocalDate.parse(eventDateStr); // Parse string to LocalDate
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid Date format. Please use `YYYY-MM-DD`.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int capacity;
        try {
            capacity = Integer.parseInt(capacityStr);
            if (capacity <= 0) {
                JOptionPane.showMessageDialog(this, "Capacity must be a positive number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Capacity. Please enter a whole number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double registrationFee;
        try {
            registrationFee = Double.parseDouble(registrationFeeStr);
            if (registrationFee < 0) {
                JOptionPane.showMessageDialog(this, "Registration Fee cannot be negative.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Registration Fee. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Event newEvent = new Event(eventName, eventDescription, eventDate, organizerName, venue, capacity, registrationFee);

        if (csvEventManager.addEvent(newEvent)) {
            JOptionPane.showMessageDialog(this, "Event '" + newEvent.getEventName() + "' added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearInputFields();
            refreshEventList(); 
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add event. It might already exist or another error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearInputFields() {
        eventNameField.setText("");
        eventDescriptionArea.setText("");
        eventDateField.setText("YYYY-MM-DD"); // Reverted to placeholder
        organizerNameField.setText("");
        venueField.setText("");
        capacityField.setText("");
        registrationFeeField.setText("");
    }

    private void refreshEventList() {
        eventComboBoxModel.removeAllElements(); 
        List<Event> events = csvEventManager.getAllEvents();
        for (Event event : events) {
            eventComboBoxModel.addElement(event); 
        }
        if (!events.isEmpty()) {
            eventComboBox.setSelectedIndex(0); 
        }
    }
}