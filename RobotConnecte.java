public abstract class RobotConnecte extends Robot implements Connectable {

    protected boolean connecte;
    protected String reseauConnecte;

    public RobotConnecte(){
        super();
        connecte=false;
        reseauConnecte=null;
    }
    @Override
    public void connecter(String reseau) throws RobotException {
        if(!enMarche){
            throw new RobotException("Allumer-le!");
        }
        if(!connecte){
        this.verifierEnergie(5);
        this.reseauConnecte = reseau;
        this.connecte = true;
        this.consommerEnergie(5);
        this.ajouterHistorique("Robot Connecté au réseau " + reseau);
        }
        else{
            throw new RobotException("Robot déjà connecté au réseau");
        }
    }
    @Override
    public void deconnecter()throws RobotException{
        if(!enMarche){
            throw new RobotException("Le robot n'est pas marche !");
        }
        if(connecte){
            this.connecte = false;
            this.reseauConnecte = null;
            this.ajouterHistorique("Le robot s'est déconnecté");
        }
        else{
            throw new RobotException("Le robot n'est pas connecté!");
        }
    }
    public void envoyerDonnees(String donnees) throws RobotException {
        if(!enMarche){
            throw new RobotException("Allumer le robot!");
        }
        if(!connecte){
            throw new RobotException("Le robot n'est pas connecté");
        }
        verifierEnergie(3); // peut lancer un RobotException
        this.consommerEnergie(3);
        this.ajouterHistorique("Données envoyées: "+donnees);
    }
}
