public class MainApplication extends javax.swing.JFrame {
    private UI.UIController uiController;
    MainApplication() {
        uiController = new UI.UIController();
        setTitle("Campus Event Management System");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        add(uiController.getMainPanel());




        setVisible(true);
    }
    public static void main(String[] args) {
        new MainApplication();
    }
}
