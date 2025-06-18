package UI;

public class UIController {
    private javax.swing.JPanel mainPanel;
    private UIPage currentPage;

    public UIController() {
        mainPanel = new javax.swing.JPanel();
        mainPanel.setLayout(new java.awt.BorderLayout());
        currentPage = null;
        showLoginPage();
    }
    public void showLoginPage() {
        if (currentPage != null) {
            mainPanel.remove(currentPage);
            currentPage = null;
        }
        currentPage = new LoginPage();
        mainPanel.add(currentPage);
    }
    public javax.swing.JPanel getMainPanel() {
        return mainPanel;
    }
        
}
