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
            System.out.println("PEUPA " + c + " " + cordes);
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
        return p.getNbSommets() == cordes.size() + 3;
    }

    public Polygone getP() {
        return p;
    }

    public Triangulation fuse (Triangulation t, Polygone poly) {
        Triangulation res = new Triangulation(poly);
        for (Corde c : this.cordes) {
            Corde c2 = new Corde (poly.getP().indexOf(this.p.getPoint(c.getP1())), poly.getP().indexOf(this.p.getPoint(c.getP2())) , poly);
            if (!res.addCorde(c2)) {
                return null;
            }
        }
        for (Corde c : t.cordes) {
            Corde c2 = new Corde (poly.getP().indexOf(t.p.getPoint(c.getP1())), poly.getP().indexOf(t.p.getPoint(c.getP2())) , poly);
            if (!res.addCorde(c2)) {
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