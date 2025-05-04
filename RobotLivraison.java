import java.util.Scanner;

public class RobotLivraison extends RobotConnecte {
    protected String ColisActuel;
    protected String Destination;
    protected boolean enlivraison;
    protected static final int ENERGIE_LIVRAISON = 15; // 10% d'énergie pour livrer un colis
    protected static final int ENERGIE_CHARGEMENT = 10;
    private Scanner scanner = new Scanner(System.in);

    public RobotLivraison() {
        super();
        this.ColisActuel = null;
        this.Destination = null;
        this.enlivraison = false;
    }
    public void deplacer(int x,int y) throws RobotException {
        double distanceDouble = Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
        int distance = (int) Math.ceil(distanceDouble);
        if (distance > 100) {
            throw new RobotException("Impossible de parcourir une distance supérieure à 100 km");
        }
        int energieNecessaire = (int) Math.ceil(0.3 * distance);
        //ici la vérification
        verifierEnergie(energieNecessaire);
        verifierMaintenance();
        int heures = (int) Math.ceil(distance / 10.0);

        //faire une boucle pour simuler le déplacement
        int energie=0;
        int heurepassee=0;
        for (int i = 0; i < distance; i++) {
            try {
                Thread.sleep(1000);
                if(energie<energieNecessaire){
                    energie++;
                    this.consommerEnergie(energie);
                }
                if(heurepassee<heures){
                    heurepassee++;
                    this.heuresUtilisation += heures;
                    this.verifierMaintenance();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.x = x;
        this.y = y;
        this.ajouterHistorique("Déplacement vers "+ this.Destination+ " en " + heures + " heures");
    }

    public void FaireLivraison(int Destx,int Desty) throws RobotException {
        this.enlivraison = true;
        deplacer(Destx, Desty);
        verifierEnergie(ENERGIE_LIVRAISON);
        this.consommerEnergie(ENERGIE_LIVRAISON);
        ajouterHistorique("Livraison terminée à " + Destination);
        this.ColisActuel = null;
        this.enlivraison = false;
        this.Destination = null;
    }
     public void effectuerTache() throws RobotException {
        if (!this.enMarche) {
            throw new RobotException("Le robot doit être démarré pour effectuer une tâche");
        }
        if(!this.connecte) {
            throw new RobotException("Le robot doit être connecté pour effectuer une tâche");
        }
        if(this.enlivraison) {
            throw new RobotException("Le robot est déjà en cours de livraison");
        }
        //si le colis est chargé
        if (this.ColisActuel!=null) {
            System.out.println("Le colis est chargé, veuillez saisir les coordonnées de la destination");
            System.out.print("Coordonnée x : ");
            int destX = scanner.nextInt();
            System.out.print("Coordonnée y : ");
            int destY = scanner.nextInt();
            FaireLivraison(destX, destY);
        }else{
            System.out.println("Voulez-vous charger un nouveau colis ? (oui/non)");
            String reponse = scanner.next().toLowerCase();// Convertir la réponse en minuscules pour pour la comparaison
            if (reponse.equals("oui")) {
                verifierEnergie(ENERGIE_CHARGEMENT);
                System.out.println("Veuillez saisir la destination du colis :");
                scanner.nextLine(); 
                String dest = scanner.nextLine();
                chargerColis(dest);
            } else if (reponse.equals("non")) {
                ajouterHistorique("En attente de colis");
            }
        }
    }
    public void chargerColis(String destination) throws RobotException{
        verifierEnergie(ENERGIE_CHARGEMENT);
        this.consommerEnergie(ENERGIE_CHARGEMENT);
        System.out.println("Veuillez saisir le nom ou la description du colis :");
        String nomColis = scanner.nextLine();
        this.ColisActuel = nomColis;
        this.Destination = destination;
        ajouterHistorique("Colis chargé vers " + destination);

}
    @Override
    public String toString() {
        return super.toString() + "Colis: " + this.ColisActuel + ", Destination: " + this.Destination + ", connecté: " + this.connecte+ ", en livraison: " + this.enlivraison + "]";
    }
    @Override
    public void arreter() throws RobotException{
        if(this.enlivraison){
            throw new RobotException("Le robot est en cours de livraison, impossible de l'arrêter");
        }
        super.arreter();
    }
}
