import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowCard implements ActionListener {
    private JPanel conteneurCartes;
    private CardLayout cardLayout;
    private String name;

    // Initialisation des paramètres
    public ShowCard(JPanel conteneurCartes, CardLayout cardLayout, String name) {
        this.conteneurCartes = conteneurCartes;
        this.cardLayout = cardLayout;
        this.name = name;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cardLayout.show(conteneurCartes, name);
    }
}