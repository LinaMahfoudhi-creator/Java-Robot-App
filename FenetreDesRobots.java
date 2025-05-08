import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreDesRobots extends JFrame {
    private CardLayout cardLayout; // Gestionnaire de disposition
    private JPanel conteneurCartes; // Conteneur pour les "cartes"

    public FenetreDesRobots() {
        //mise en place de fenêtre
        this.setTitle("Jeu de Robots");
        this.setSize(500, 700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());


        cardLayout = new CardLayout();
        conteneurCartes = new JPanel(cardLayout);
        conteneurCartes.add(new CardPrincipal(),"Accueil");
        //conteneurCartes.add(new CardMap(),"Map");
        conteneurCartes.add(new RobotCard(),"Robot");
        this.add(conteneurCartes, BorderLayout.CENTER);


        //JPanel des boutons
        JPanel conteneurBoutons = new JPanel();
        conteneurBoutons.setLayout(new BoxLayout(conteneurBoutons, BoxLayout.X_AXIS));
        Button bouton1= new Button("Acceuil");
        //Button bouton2= new Button("Map");
        Button bouton3= new Button("Robot");
        //ajout des boutons
        conteneurBoutons.add(bouton1);
        //conteneurBoutons.add(bouton2);
        conteneurBoutons.add(bouton3);
        this.add(conteneurBoutons, BorderLayout.SOUTH);

        //ajout des listeners
        bouton1.addActionListener(new ShowCard(conteneurCartes,cardLayout,"Accueil"));
        //bouton2.addActionListener(new ShowCard(conteneurCartes,cardLayout,"Map"));
        bouton3.addActionListener(new ShowCard(conteneurCartes,cardLayout,"Robot"));
        this.setVisible(true);
    }
}
