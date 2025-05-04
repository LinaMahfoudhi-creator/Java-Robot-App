public class Tester {
    public static void main(String[] args) {
        try {
            RobotLivraison robot1 = new RobotLivraison();
            System.out.println(robot1);
            System.out.println(robot1.getHistorique());
            robot1.arreter();
            robot1.demarrer();
            robot1.connecter("réseau 1");
            robot1.effectuerTache();

            System.out.println(robot1);
            System.out.println(robot1.getHistorique());

            robot1.effectuerTache();
            System.out.println(robot1);
            System.out.println(robot1.getHistorique());
        }
        catch (RobotException e) {
            System.out.println(e);
        }
    }
}
