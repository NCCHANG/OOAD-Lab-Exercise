// UIPage.java
package UI;

public abstract class UIPage extends javax.swing.JPanel {
    protected UIController controller;

    // Add a constructor that takes UIController
    public UIPage(UIController controller) {
        this.controller = controller;
    }

    // You might also keep a setter for flexibility, but constructor injection is often cleaner
    public void setController(UIController controller) {
        this.controller = controller;
    }

    abstract public void initUI();
}