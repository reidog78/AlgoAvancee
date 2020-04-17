package essaisSuccessifs;

import java.util.ArrayList;

public class Triangulation {

    private Polygone p;
    private ArrayList<Corde> cordes;
    private double longueur;
    private boolean valide;

    public Triangulation(Polygone p) {
        this.p = p;
        this.cordes = new ArrayList<>();
        this.longueur = 0;
        this.valide = false;
    }

    public double getLongueur() {
        return longueur;
    }

    public boolean addCorde (Corde c) {
        if (valideCorde(c)) {
            cordes.add(c);
            longueur += c.longueur();
            if (cordes.size() == p.getNbSommets() - 3) {
                valide = true;
            }
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

    public ArrayList<Corde> getCordes() {
        return cordes;
    }

    public boolean isValide() {
        return valide;
    }

    public Polygone getP() {
        return p;
    }

    public Triangulation fuse (Triangulation t, Polygone p) {
        Triangulation res = new Triangulation(p);
        for (Corde c : this.cordes) {
            if (!res.addCorde(c)) {
                return null;
            }
        }
        for (Corde c : t.cordes) {
            if (!res.addCorde(c)) {
                return null;
            }
        }
        return res;
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