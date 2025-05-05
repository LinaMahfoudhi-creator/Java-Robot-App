import java.util.ArrayList;

public class Ligne {

    private static ArrayList<String> Tab_station = new ArrayList<>();
    private static int nbstation=0;
    public String stdepart;
    public String starrivee;
    private float poids;


    public Ligne(String stdepart, String starrivee, float poids) {
        this.stdepart = stdepart;
        this.starrivee = starrivee;
        this.poids = poids;

        if (!Tab_station.contains(stdepart)) {
            Tab_station.add(stdepart);
            nbstation++;
        }
        if(!Tab_station.contains(starrivee)) {
            Tab_station.add(starrivee);
            nbstation++;
        }

    }
    public float getPoids() {
        return poids;
    }
    public void setPoids(float poids) {
        this.poids = poids;
    }
    public static int getnbstation() {
        return nbstation;
    }
    public static ArrayList<String> getTab_station() {
        return Tab_station;
    }
    public static void setTab_station(ArrayList<String> tab_station) {
        Tab_station = tab_station;
        nbstation = Tab_station.size();
    }
    public String toString() {
        return "Pour aller de la station "+stdepart +" à la station "+starrivee+" la distance est "+(int)(this.poids)+" minutes "+Integer.parseInt(String.valueOf(this.poids).split("\\.")[1])+" secondes ";
    }

}
