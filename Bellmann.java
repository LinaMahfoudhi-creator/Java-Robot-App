import java.util.ArrayList;

public class Bellmann {
    public static void main(String[] args) {
        Ligne AB=new Ligne("A","B", 4.8F);
        Ligne AC=new Ligne("A","C", 2);
        Ligne BD=new Ligne("B","D", 10.12F);
        Ligne BC=new Ligne("B","C", 5);
        Ligne CE=new Ligne("C","E", 3.58F);
        Ligne ED=new Ligne("E","D", 4F);
        Ligne DF=new Ligne("D","F", 11);
        System.out.println(Ligne.getTab_station());
        ArrayList<Ligne> tab_line = new ArrayList<>();
        tab_line.add(AB);
        tab_line.add(AC);
        tab_line.add(BD);
        tab_line.add(BC);
        tab_line.add(CE);
        tab_line.add(ED);
        tab_line.add(DF);
        Reseau reseau = new Reseau(tab_line);

        /*for(Ligne line:reseau.Tab_line){
            System.out.println(line);
        }*/

        float[] dist = new float[6];
        int j = reseau.index("A");

        for (int i = 0; i < 6; i++) {
            if (i == j) {
                dist[i] = 0;
            } else {
                dist[i] = Float.POSITIVE_INFINITY;
            }
        }

        int a;
        int b;
        String[] pred = new String[6];

        for( int k = 0 ; k<7;k++){
            for(int i =0 ; i<reseau.nbline ;i++){
                a=reseau.index(reseau.Tab_line.get(i).stdepart);
                b=reseau.index(reseau.Tab_line.get(i).starrivee);
                if(dist[b]>dist[a]+reseau.Tab_line.get(i).getPoids()){
                    dist[b]=dist[a]+reseau.Tab_line.get(i).getPoids();
                    pred[b]=reseau.Tab_line.get(i).stdepart;
                }
            }

        }
        for(int i=0;i<6;i++){
            System.out.print("Pour aller de "+Ligne.getTab_station().get(0)+" à "+Ligne.getTab_station().get(i)+" " +dist[i] +"\n");
        }
        System.out.println("");
        for(int i=0;i<6;i++){
            System.out.print("Le précédeur direct dans le chemin le plus court de "+Ligne.getTab_station().get(0)+" vers "+Ligne.getTab_station().get(i)+" est " +pred[i]+"\n");
        }

    }
}
