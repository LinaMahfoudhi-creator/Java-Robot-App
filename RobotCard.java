import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RobotCard extends JPanel {
    private RobotLivraison robot;
    private JPanel container;
    private ButtonsPanel buttonsPanel=new ButtonsPanel();
    private PanelHistorique HistoriquePanel;

    //ajout des listeners
    public class BoutonListener implements ActionListener{
        JButton bouton;
        public BoutonListener(JButton button) {
            this.bouton=button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (bouton.getText().equals("Connect")) {
                    robot.connecter("Livraison");
                } else if (bouton.getText().equals("Disconnect")) {
                    robot.deconnecter();
                } else if (bouton.getText().equals("Turn On")) {
                    robot.demarrer();
                } else if (bouton.getText().equals("Turn Off")) {
                    robot.arreter();
                }
                else if (bouton.getText().equals("Recharger")) {
                    String input = JOptionPane.showInputDialog(null, "Recharger le de combien:", "Input Required", JOptionPane.QUESTION_MESSAGE);
                    int number = Integer.parseInt(input);
                    robot.recharger(number);
                }
                HistoriquePanel.updateHistory(robot.historiqueActions.get(robot.historiqueActions.size() - 1));
                System.out.println(robot);
            }
            catch (RobotException e1) {
                HistoriquePanel.updateHistory(e1.getMessage());
            }
        }
    }


    public RobotCard() {
        // Set the layout and background
        this.setLayout(new BorderLayout());
        JPanel gifPanel = new GIFBackgroundPanel("Images/BMO LOVE.gif");
        this.add(gifPanel, BorderLayout.CENTER);
        robot = new RobotLivraison();
        robot.energie=0;

        container = new JPanel(new BorderLayout());
        HistoriquePanel = new PanelHistorique(robot.getHistorique());
        container.add(HistoriquePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
        buttonPanel.setLayout(boxLayout);
        buttonPanel.add(buttonsPanel);
        buttonPanel.add(Box.createVerticalStrut(10));
        container.add(buttonPanel, BorderLayout.SOUTH);
        this.add(container, BorderLayout.EAST);

        //ajout des listeners
        buttonsPanel.Connecter.addActionListener(new BoutonListener(buttonsPanel.Connecter));
        buttonsPanel.Deconnecter.addActionListener(new BoutonListener(buttonsPanel.Deconnecter));
        buttonsPanel.Allumer.addActionListener(new BoutonListener(buttonsPanel.Allumer));
        buttonsPanel.Eteindre.addActionListener(new BoutonListener(buttonsPanel.Eteindre));
        buttonsPanel.Recharger.addActionListener(new BoutonListener(buttonsPanel.Recharger));
        buttonsPanel.clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ch1=robot.toString().substring(0,robot.toString().indexOf("Heures"));
                String ch2=robot.toString().substring(robot.toString().indexOf("Heures"),robot.toString().indexOf("Destination"));
                String ch3=robot.toString().substring(robot.toString().indexOf("Destination"));
                HistoriquePanel.Historique.setText(ch1+"\n"+ch2+"\n"+ch3+"\n");
            }
        });
    }
}
