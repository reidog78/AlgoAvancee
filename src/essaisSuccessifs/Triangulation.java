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

    public ArrayList<Corde> getCordes() {
        return cordes;
    }

    public boolean isValide() {
        return p.getNbSommets() == cordes.size() + 3;
    }

    public Polygone getP() {
        return p;
    }

    // Fusionne deux triangulations en une triangulation d'un autre polygone
    public Triangulation fuse (Triangulation t, Polygone poly) {
        Triangulation res = new Triangulation(poly);
        for (Corde c : this.cordes) {
            // On change les identifiants des points pour qu'ils fonctionnent dans le nouveau polygone
            Corde c2 = new Corde (poly.getP().indexOf(this.p.getPoint(c.getP1())), poly.getP().indexOf(this.p.getPoint(c.getP2())) , poly);
            if (!res.addCorde(c2)) {
                return null;
            }
        }
        for (Corde c : t.cordes) {
            // On change les identifiants des points pour qu'ils fonctionnent dans le nouveau polygone
            Corde c2 = new Corde (poly.getP().indexOf(t.p.getPoint(c.getP1())), poly.getP().indexOf(t.p.getPoint(c.getP2())) , poly);
            if (!res.addCorde(c2)) {
                return null;
            }
        }
        return res;
    }

    public boolean valideCorde (Corde c) {
        for (Corde cEx : cordes) {
            // Si la corde existe déjà, elle n'est pas valide
            if (cEx.getP1() == c.getP1() && cEx.getP2() == c.getP2()) {
                return false;
            }
            // Si la corde en intersecte une autre, elle n'est pas valide
            if (cEx.intersection(c)) {
                return false;
            }
        }
        return true;
    }
}