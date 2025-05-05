
import java.time.*;
import java.time.format.*;
import java.util.ArrayList;
import java.util.Locale;

public abstract class Robot {

    protected String id; //  id généré aléatoirement
    protected int x; //  coordonnées abscisse
    protected int y; //  coordonnées à l'ordonnée
    protected int energie; //  entre 0 et 100
    protected int heuresUtilisation; //  heures avant maintenance (<100)
    protected boolean enMarche; //  éteint ou allumé
    protected ArrayList<String> historiqueActions= new ArrayList<>(); // documentation des actions
    protected ArrayList<String> IDS= new ArrayList<>();


    public void ajouterHistorique(String action) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy HH :mm :ss", Locale.FRENCH);
        String format= now.format(formatter);
        historiqueActions.add(format+" "+action);
    }

    public Robot() {
        do{
            this.id = RandomGenerator.idGenerator();
        }
        while(this.IDS.contains(this.id));
        this.IDS.add(this.id);
        this.x = 0;
        this.y = 0;
        this.energie = 100;
        this.heuresUtilisation =0;
        this.enMarche = true;
        ajouterHistorique("Démarrage du robot");
    }

    public void arreter() throws RobotException {
        if(enMarche){
            this.enMarche=false;
            this.ajouterHistorique("Arrêt du robot");
        }
        else{
            throw new RobotException("Robot déja éteint! ");
        }
    }

    public String getHistorique() {
        return this.historiqueActions.toString();
    }

    public void recharger(int energie) {
        this.energie +=energie;
        if(this.energie > 100){
            this.energie = 100;
        }
        this.ajouterHistorique("Recharge du robot de "+this.energie+"%");
    }

    @Override
    public String toString(){
        return ("RobotLivraison [ID: "+this.id+", Position: ("+this.x+","+this.y+"), Energie: "+this.energie+", Heures: "+this.heuresUtilisation+", Allumé: "+this.enMarche+" ,");
    }

    public void consommerEnergie(int energie){
        this.energie -= energie;
    } /*pas besoin de vérifier l'énergie ici car verfierEnergie est toujours appelée
     avant consommer dans toutes les autres méthodes, cette dernière lancera
    alors l'exception */

    public boolean verifierEnergie(int energie) throws EnergieInsuffisanteException{
        if(this.energie < energie){
            throw new EnergieInsuffisanteException();
        }
        return true;
    }

    public boolean verifierMaintenance() throws MaintenanceRequiseException{
        if(this.heuresUtilisation >= 100){
            throw new MaintenanceRequiseException();
        }
        return true;
    }
    public void demarrer() throws RobotException {
        if (!this.enMarche) {
            if (this.energie < 10) {
                throw new RobotException("Recharger le pour l'allumer");
            }
            this.energie -= 10;
            this.enMarche = true;
            this.ajouterHistorique("robot réallumé");
        }
        else{
            throw new RobotException("Déjà allumé");
        }
    }
    public abstract void effectuerTache()throws RobotException;
    public abstract void deplacer(int x,int y) throws RobotException;

}
