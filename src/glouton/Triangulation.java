package glouton;

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

    public double getLongueur() {
        return longueur;
    }

    public boolean addCorde (Corde c) {
        if (valideCorde(c)) {
            cordes.add(c);
            longueur += c.longueur();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Triangulation{" +
                "cordes=" + cordes +
                "poly=" + p +
                "l=" + longueur +
                '}';
    }


    public Polygone getP() {
        return p;
    }

    // Fusionne deux triangulations en une triangulation d'un autre polygone
    public Triangulation fuse (Triangulation t) {
        for (Corde c : t.cordes) {
            Corde c2 = new Corde (c.getP1(), c.getP2() , this.p);
            if (!this.addCorde(c2)) {
                return null;
            }
        }
        return this;
    }

        public boolean valideCorde (Corde c) {
            for (Corde cEx : cordes) {
                // Si la corde existe déjà, elle n'est pas valide
                if (cEx.getP1() == c.getP1() && cEx.getP2() == c.getP2() || cEx.getP1() == c.getP2() && cEx.getP2() == c.getP1()) {
                    return false;
                }
            }
        return true;
    }
}