import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RobotCard extends JPanel {
    private RobotLivraison robot;
    private JPanel container;
    private ButtonsPanel buttonsPanel=new ButtonsPanel();
    private PanelHistorique HistoriquePanel;
    RobotGridPanel gridPanel;

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
                else if (bouton.getText().equals("Effectuer une Tache")) {
                    String input = JOptionPane.showInputDialog(null, robot.effectuerTache(), "Input Required", JOptionPane.QUESTION_MESSAGE);
                    if (input.equals("oui") && robot.ColisActuel==null) {
                        String input2 = JOptionPane.showInputDialog(null, "Veuillez saisir le nom du colis:", "Input Required", JOptionPane.QUESTION_MESSAGE);
                        String input3 = JOptionPane.showInputDialog(null, "Veuillez saisir la destination:", "Input Required", JOptionPane.QUESTION_MESSAGE);
                        robot.chargerColis(input3, input2);
                    }
                    else if (input.equals("non") && robot.ColisActuel==null) {
                        robot.ajouterHistorique("En attente de Colis");
                    }
                    else if (input.equals("oui")){
                        String input2 = JOptionPane.showInputDialog(null, "Veuillez saisir Destx:", "Input Required", JOptionPane.QUESTION_MESSAGE);
                        String input3 = JOptionPane.showInputDialog(null, "Veuillez saisir Desty:", "Input Required", JOptionPane.QUESTION_MESSAGE);
                        int Destx = Integer.parseInt(input2);
                        int Desty = Integer.parseInt(input3);
                        robot.FaireLivraison(Destx, Desty);
                        System.out.println(robot.historiqueActions.get(robot.historiqueActions.size() - 1));
                        int gridX = Destx / 10;
                        int gridY = Desty / 10;
                        String trash=gridPanel.setRobotPosition(gridX, gridY);
                        if (trash!=null) {
                        robot.ajouterHistorique(robot.historiqueActions.get(robot.historiqueActions.size() - 1)+ "\n" + trash);
                        }

                    }
                    else if (input.equals("non")){
                        throw new RobotException("Livraison annulée");
                    }
                    else{
                        throw new RobotException("Donner une reponse valide");
                    }
                }
                else if (bouton.getText().equals("Recharger")) {
                    String input = JOptionPane.showInputDialog(null, "Recharger le de combien:", "Input Required", JOptionPane.QUESTION_MESSAGE);
                    int number = Integer.parseInt(input);
                    robot.recharger(number);
                }
                HistoriquePanel.updateHistory(robot.historiqueActions.get(robot.historiqueActions.size() - 1));
                System.out.println(robot);
            }
            catch (Exception e1) {
                if (e1 instanceof RobotException) {
                HistoriquePanel.updateHistory(e1.getMessage());
                } else {
                    HistoriquePanel.updateHistory("Transaction annulée");
                }
            }
        }
    }
    public RobotCard() {
        // Set the layout and background
        this.setLayout(new BorderLayout());
        JPanel gifPanel = new GIFBackgroundPanel("Images/BMO LOVE.gif");
        this.add(gifPanel, BorderLayout.CENTER);
        robot = new RobotLivraison();

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        HistoriquePanel = new PanelHistorique(robot.getHistorique());
        container.add(HistoriquePanel);
        gridPanel = new RobotGridPanel(4,5);
        container.add(gridPanel);

        JPanel buttonPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
        buttonPanel.setLayout(boxLayout);
        buttonPanel.add(buttonsPanel);
        buttonPanel.add(Box.createVerticalGlue());
        container.add(buttonPanel);
        this.add(container, BorderLayout.EAST);

        //ajout des listeners
        buttonsPanel.Connecter.addActionListener(new BoutonListener(buttonsPanel.Connecter));
        buttonsPanel.Deconnecter.addActionListener(new BoutonListener(buttonsPanel.Deconnecter));
        buttonsPanel.Allumer.addActionListener(new BoutonListener(buttonsPanel.Allumer));
        buttonsPanel.Eteindre.addActionListener(new BoutonListener(buttonsPanel.Eteindre));
        buttonsPanel.Recharger.addActionListener(new BoutonListener(buttonsPanel.Recharger));
        buttonsPanel.EffectuerTache.addActionListener(new BoutonListener(buttonsPanel.EffectuerTache));
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
