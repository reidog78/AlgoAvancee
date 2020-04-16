package essaisSuccessifs;

import java.util.ArrayList;

public class Triangulation {

    private Polygone p;
    private ArrayList<Corde> cordes;
    private double longueur;

    public Triangulation(Polygone p) {
        this.p = p;
        this.cordes = new ArrayList<>();
        this.longueur = 0;
    }

    public void addCorde (Corde c) {
        if (valideCorde(c)) {
            cordes.add(c);
            longueur += c.longueur();
        } else {
            System.out.println("DAB");
        }
    }

    @Override
    public String toString() {
        return "Triangulation{" +
                "cordes=" + cordes +
                '}';
    }

    public boolean valideCorde (Corde c) {
        for (Corde cEx : cordes) {
            if (cEx.getP1() == c.getP1() && cEx.getP2() == c.getP2()) {
                return false;
            }
            if (cEx.intersection(c)) {
                return false;
            }
        }
        return true;
    }
}