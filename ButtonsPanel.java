import javax.swing.*;
import java.awt.*;
public class ButtonsPanel extends JPanel {
     JButton Connecter;
     JButton Deconnecter;
     JButton Allumer;
     JButton Eteindre;
     JButton clear;
     JButton Recharger;
    public ButtonsPanel() {
        this.setPreferredSize(new Dimension(200, 100));
        this.setLayout(new GridLayout(2,3));
        this.add(Connecter = new JButton("Connect"));
        this.add(Deconnecter = new JButton("Disconnect"));
        this.add(Allumer = new JButton("Turn On"));
        this.add(Eteindre = new JButton("Turn Off"));
        this.add(clear = new JButton("Clear"));
        this.add(Recharger = new JButton("Recharger"));
    }
}
