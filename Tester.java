public class Tester {
    public static void main(String[] args) {
        try {
            RobotConnecte robot1=new RobotConnecte();
            robot1.getHistorique();
            robot1.arreter();
            robot1.demarrer();
            robot1.energie=90;
            robot1.connecter("premier réseau");
            System.out.println(robot1.getHistorique());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
