import java.lang.reflect.Array;
import java.util.ArrayList;

public class Polygone {

    private int nbSommets;
    private Point[] p;

    public Polygone(Point[] p) {
        this.nbSommets = p.length;
        this.p = p;
    }

    public int getNbSommets() {
        return nbSommets;
    }

    public void setNbSommets(int nbSommets) {
        this.nbSommets = nbSommets;
    }

    public void triangulerSuccessifs () {

        ArrayList<Integer[]> triangulation = new ArrayList<>();

        for (int i = 0; i < nbSommets; i++) {
            for (int j = 0; j < nbSommets; i++) {
                for (int k = 0; k < triangulation.size(); k++)
                if (valideCorde(i, j, triangulation.get(k)[0], triangulation.get(k)[1])) {
                    Integer[] l;
                    l[0] = i;
                    l[1] = j;
                    triangulation.add(l);
                }
            }
        }
    }
    


}