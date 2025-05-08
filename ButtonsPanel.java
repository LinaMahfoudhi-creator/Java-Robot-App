import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    JButton Connecter;
    JButton Deconnecter;
    JButton Allumer;
    JButton Eteindre;
    JButton clear;
    JButton Recharger;
    JButton EffectuerTache;

    public ButtonsPanel() {
        this.setPreferredSize(new Dimension(200, 100));
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);  // Add some padding between buttons

        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        this.add(Connecter = new JButton("Connect"), gbc);

        gbc.gridx = 1;
        this.add(Deconnecter = new JButton("Disconnect"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(Allumer = new JButton("Turn On"), gbc);


        gbc.gridx = 1;
        this.add(Eteindre = new JButton("Turn Off"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(clear = new JButton("Clear"), gbc);

        gbc.gridx = 1;
        this.add(Recharger = new JButton("Recharger"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        this.add(EffectuerTache = new JButton("Effectuer une Tache"), gbc);
    }
}
