import java.util.ArrayList;

public class Reseau {
    public ArrayList<Ligne> Tab_line;
    public int nbline;
    public Reseau() {
        Tab_line = new ArrayList<>();
        nbline=0;
    }
    public Reseau(ArrayList<Ligne> Tab_line) {
        this.Tab_line=Tab_line;
        this.nbline=Tab_line.size();
    }
    public void add_line(String stdepart,String starrivee,float poids) {
        Tab_line.add(new Ligne(stdepart,starrivee,poids));
    }
    public ArrayList<String> Adjascence(String stdepart){
        ArrayList<String> Adjascence = new ArrayList<>();
        for (Ligne line:Tab_line) {
            if (line.stdepart.equals(stdepart)) {
                Adjascence.add(line.starrivee);
            }
        }
        return Adjascence;
    }
    public int index(String station){
        for (int i = 0; i < Tab_line.size(); i++) {
            if (Ligne.getTab_station().get(i).equals(station)) {
                return i;
            }
        }
        return -1;
    }
}
