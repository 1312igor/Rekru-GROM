import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Formularz extends JFrame {

    private JLabel statusLabel;
    private JLabel nameLabel;

    public Formularz() {
        setTitle("Rekrutacja GROM");
        setSize(500, 400);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        ImageIcon shieldIcon = new ImageIcon(getClass().getResource("/tarczaG.png"));
        Image img = shieldIcon.getImage();
        Image scaledIMG = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        shieldIcon = new ImageIcon(scaledIMG);

        JLabel titleLabel = new JLabel("SYSTEM REKRUTACJI AGENCJI GROM");
        titleLabel.setIcon(shieldIcon);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setIconTextGap(15);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        nameLabel = new JLabel("Kandydat: [Brak]", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusLabel = new JLabel("Oczekiwanie na kandydata...", SwingConstants.CENTER);
        statusLabel.setForeground(Color.GRAY);

        centerPanel.add(nameLabel);
        centerPanel.add(statusLabel);
        add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton startButton = new JButton("ROZPOCZNIJ PROCES");
        startButton.setPreferredSize(new Dimension(200, 40));
        startButton.addActionListener(e -> startRekrutacja());
        southPanel.add(startButton);

        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(southPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Czy na pewno chcesz przerwać rekrutację i usunąć swoje dane?",
                        "Potwierdzenie wyjścia",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startRekrutacja() {
        String imie = JOptionPane.showInputDialog(this, "Podaj swoje imię:");

        if (imie == null || imie.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Błąd: Imię nie może być puste!", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }
        nameLabel.setText("Kandydat: " + imie);

        int lojalnosc = JOptionPane.showConfirmDialog(
                this,
                "Czy przysięgasz chronić tajemnice agencji nawet pod groźbą braku kawy?",
                "Test Lojalności",
                JOptionPane.YES_NO_OPTION
        );

        if (lojalnosc != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Przykro nam, szukamy osób bardziej oddanych.");
            resetujAplikacje();
            return;
        }

        String[] opcje = {"Programista Java", "Analityk Danych", "Agent Terenowy"};
        int wybor = JOptionPane.showOptionDialog(
                this,
                "Wybierz swoją specjalizację:",
                "Wybór Ścieżki",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcje,
                opcje[0]
        );

        String komunikatKoncowy = "";
        switch (wybor) {
            case 0 -> komunikatKoncowy = "Wybrałeś drogę kodu. Przygotuj się na debugowanie rzeczywistości.";
            case 1 -> komunikatKoncowy = "Liczby nie kłamią. Twój umysł będzie naszym najostrzejszym narzędziem.";
            case 2 -> komunikatKoncowy = "Cień to Twój dom. Powodzenia w terenie.";
            default -> { return; }
        }

        statusLabel.setText("Status: Rekrutacja zakończona sukcesem.");
        JOptionPane.showMessageDialog(this,
                "PODSUMOWANIE:\nKandydat: " + imie + "\nSpecjalizacja: " + opcje[wybor] + "\n\n" + komunikatKoncowy);
    }

    private void resetujAplikacje() {
        nameLabel.setText("Kandydat: [Brak]");
        statusLabel.setText("Oczekiwanie na kandydata...");
    }
}

