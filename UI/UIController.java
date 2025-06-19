package UI;

public class UIController {
    private javax.swing.JPanel mainPanel;
    private UIPage currentPage;

    public UIController() {
        mainPanel = new javax.swing.JPanel();
        mainPanel.setLayout(new java.awt.BorderLayout());
        currentPage = null;
        showRegisterPage();
    }
    public void showLoginPage() {
        if (currentPage != null) {
            mainPanel.remove(currentPage);
            currentPage = null;
        }
        currentPage = new LoginPage();
        mainPanel.add(currentPage);
    }
    public void showEventOrganizerFunctionalityPage() {
        if (currentPage != null) {
            mainPanel.remove(currentPage);
            currentPage = null;
        }
        currentPage = new EventOrganizerFunctionalityPage();
        mainPanel.add(currentPage);
    }
    public void showCreateEventPage() {
        if (currentPage != null) {
            mainPanel.remove(currentPage);
            currentPage = null;
        }
        currentPage = new CreateEventPage();
        mainPanel.add(currentPage);
    }

    public void showDeleteEventPage() {
        if (currentPage != null) {
            mainPanel.remove(currentPage);
            currentPage = null;
        }
        currentPage = new DeleteEventPage();
        mainPanel.add(currentPage);
    }

    public void showUpdateEventPage() {
        if (currentPage != null) {
            mainPanel.remove(currentPage);
            currentPage = null;
        }
        currentPage = new UpdateEventPage();
        mainPanel.add(currentPage);
    }

    public void showRegisterPage() {
        if (currentPage != null) {
            mainPanel.remove(currentPage);
            currentPage = null;
        }
        currentPage = new RegisterPage();
        mainPanel.add(currentPage);
    }
    public javax.swing.JPanel getMainPanel() {
        return mainPanel;
    }
        
}
