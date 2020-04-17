package glouton;


import java.util.ArrayList;
import java.util.List;

public class Polygone {

    private int nbSommets;
    private List<Point> p;
    private ArrayList<Corde> c;

    public Polygone(List<Point> p) {
        this.nbSommets = p.size();
        this.p = p;
        this.c = new ArrayList<>();
    }

    public ArrayList<Corde> getC() {
        return c;
    }

    public void setC(ArrayList<Corde> c) {
        this.c = c;
    }

    public int getNbSommets() {
        return nbSommets;
    }

    public void setNbSommets(int nbSommets) {
        this.nbSommets = nbSommets;
    }

    public void setP(ArrayList<Point> p) {
        this.p = p;
    }

    public Point getPoint (int id) { return p.get(id); }

    public Corde cordeMin () {
        Corde c = new Corde(p.get(0), p.get(2), this);
        for (int i = 0; i < nbSommets; i++) {
            Corde c2 = new Corde(p.get(i), p.get((i+2)%nbSommets), this);
            if (c2.longueur() < c.longueur()) {
                c = c2;
            }
        }
        return c;
    }


    public List<Point> getP() {
        return p;
    }

    public Triangulation trianguler () {
        // On retourne une triangulation vide si le polygone a moins de 3 côtés
        if (nbSommets <= 3) {
            return new Triangulation(this);
        }

        Corde c = cordeMin();
        Triangulation t = new Triangulation(this);
        t.addCorde(c);

        List<Point> pointsRestants = new ArrayList<>(p);
        pointsRestants.remove((p.indexOf(c.getP1()) + 1) % nbSommets);

        Triangulation t2 = new Polygone(pointsRestants).trianguler();
        System.out.println(t2);
        return t.fuse(t2);

    }

    @Override
    public String toString() {
        return "essaisSuccessifs.Polygone [nbSommets=" + nbSommets + ", p=" + p + "]";
    }




}