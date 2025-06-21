package UI;

public class UIController {
    private javax.swing.JPanel mainPanel;
    private UIPage currentPage;

    private void removeCurrentPage() {
        if (currentPage != null) {
            mainPanel.remove(currentPage);
            currentPage = null;
        }
    }

    public UIController() {
        mainPanel = new javax.swing.JPanel();
        mainPanel.setLayout(new java.awt.BorderLayout());
        currentPage = null;
        showCreateEventPage();
    }
    public void showLoginPage() {
        removeCurrentPage();
        currentPage = new LoginPage();
        mainPanel.add(currentPage);
    }
    public void showEventOrganizerFunctionalityPage() {
        removeCurrentPage();
        currentPage = new EventOrganizerFunctionalityPage();
        mainPanel.add(currentPage);
    }
    public void showCreateEventPage() {
        removeCurrentPage();
        currentPage = new CreateEventPage();
        mainPanel.add(currentPage);
    }

    public void showDeleteEventPage() {
        removeCurrentPage();
        currentPage = new DeleteEventPage();
        mainPanel.add(currentPage);
    }

    public void showUpdateEventPage() {
        removeCurrentPage();
        currentPage = new UpdateEventPage();
        mainPanel.add(currentPage);
    }

    public void showRegisterPage() {
        removeCurrentPage();
        currentPage = new RegisterPage();
        mainPanel.add(currentPage);
    }
    public void showBillPage() {
        removeCurrentPage();
        currentPage = new BillPage();
        mainPanel.add(currentPage);
    }
    public javax.swing.JPanel getMainPanel() {
        return mainPanel;
    }
        
}
