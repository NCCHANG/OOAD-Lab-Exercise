// UIController.java
package UI;

import Data.CsvEventManager; // Import the CsvEventManager

public class UIController {
    private javax.swing.JPanel mainPanel;
    private UIPage currentPage;
    private CsvEventManager csvEventManager; // Declare CsvEventManager instance

    private void removeCurrentPage() {
        if (currentPage != null) {
            mainPanel.remove(currentPage);
            // Optionally, call a dispose method on currentPage if it has resources to release
            // if (currentPage instanceof YourDisposablePage) {
            //     ((YourDisposablePage) currentPage).dispose();
            // }
            currentPage = null;
        }
    }

    public UIController() {
        mainPanel = new javax.swing.JPanel();
        mainPanel.setLayout(new java.awt.BorderLayout());
        currentPage = null;
        csvEventManager = new CsvEventManager(); // Initialize CsvEventManager here

        // Start with the login page
        showLoginPage();
    }

    // Method to get the CsvEventManager instance (can be used by pages if needed)
    public CsvEventManager getCsvEventManager() {
        return csvEventManager;
    }

    // --- Page Navigation Methods ---
    public void showLoginPage() {
        removeCurrentPage();
        // Pass both controller and csvEventManager for consistency
        currentPage = new LoginPage(this, csvEventManager);
        mainPanel.add(currentPage);
        mainPanel.revalidate(); // Ensure UI updates
        mainPanel.repaint();    // Ensure UI updates
    }

    public void showEventOrganizerFunctionalityPage() {
        removeCurrentPage();
        // Pass both controller and csvEventManager
        currentPage = new EventOrganizerFunctionalityPage(this, csvEventManager);
        mainPanel.add(currentPage);
        mainPanel.revalidate(); // Ensure UI updates
        mainPanel.repaint();    // Ensure UI updates
    }

    public void showCreateEventPage() {
        removeCurrentPage();
        // Pass both controller and csvEventManager
        currentPage = new CreateEventPage(this, csvEventManager);
        mainPanel.add(currentPage);
        mainPanel.revalidate(); // Ensure UI updates
        mainPanel.repaint();    // Ensure UI updates
    }

    public void showDeleteEventPage() {
        removeCurrentPage();
        // Pass both controller and csvEventManager
        currentPage = new DeleteEventPage(this, csvEventManager);
        mainPanel.add(currentPage);
        mainPanel.revalidate(); // Ensure UI updates
        mainPanel.repaint();    // Ensure UI updates
    }

    public void showUpdateEventPage() {
        removeCurrentPage();
        // Pass both controller and csvEventManager
        currentPage = new UpdateEventPage(this, csvEventManager);
        mainPanel.add(currentPage);
        mainPanel.revalidate(); // Ensure UI updates
        mainPanel.repaint();    // Ensure UI updates
    }

    public void showRegisterPage() {
        removeCurrentPage();
        // Pass both controller and csvEventManager
        currentPage = new RegisterPage(this, csvEventManager);
        mainPanel.add(currentPage);
        mainPanel.revalidate(); // Ensure UI updates
        mainPanel.repaint();    // Ensure UI updates
    }

    public void showBillPage() {
        removeCurrentPage();
        // Pass both controller and csvEventManager
        currentPage = new BillPage(this, csvEventManager);
        mainPanel.add(currentPage);
        mainPanel.revalidate(); // Ensure UI updates
        mainPanel.repaint();    // Ensure UI updates
    }

    public javax.swing.JPanel getMainPanel() {
        return mainPanel;
    }
}