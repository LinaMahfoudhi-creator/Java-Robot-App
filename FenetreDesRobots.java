import javax.swing.*;
import java.awt.*;

public class FenetreDesRobots extends JFrame {
    private CardLayout cardLayout; // Gestionnaire de disposition
    private JPanel conteneurCartes; // Conteneur pour les "cartes"

    public FenetreDesRobots() {
        this.setTitle("Jeu de Robots");
        this.setSize(400, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());


        cardLayout = new CardLayout();
        conteneurCartes = new JPanel(cardLayout);
        conteneurCartes.add(new CardPrincipal(),"Accueil");
        this.add(conteneurCartes, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
