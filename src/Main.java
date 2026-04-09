import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Formularz window = new Formularz();
            window.setVisible(true);
        });
    }
}