import javax.swing.*;
import java.awt.*;

public class PanelHistorique extends JPanel {
    public JTextArea Historique;
    public PanelHistorique(String update) {

        this.Historique = new JTextArea();
        Historique.setFont(new Font("Arial", Font.BOLD, 12));
        this.Historique.setEditable(false);
        this.Historique.setText(update+"    \n");
        this.add(Historique);
    }
    public void updateHistory(String update) {

        this.Historique.setText(Historique.getText() + update + "\n");
    }
}
