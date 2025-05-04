import javax.swing.*;
import java.awt.*;

public class FenetreDesRobots extends JFrame {
    public FenetreDesRobots() {
        this.setTitle("Jeu de Robots");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
    }
}
