package essaisSuccessifs;

import java.util.ArrayList;

public class Polygone {

    private int nbSommets;
    private ArrayList<Point> p;
    private ArrayList<Corde> c;

    public Polygone(ArrayList<Point> p) {
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

    public Triangulation trianguler () {
        Triangulation t = new Triangulation(this);

        for (int i = 0; i < nbSommets; i++) {
            ArrayList<Corde> cordesPossible = cordesPossibles(i);

            System.out.println("Etape " + i + "  : " + cordesPossible);
        }

        return t;
    }

    public ArrayList<Corde> cordesPossibles (int idSommet) {
        ArrayList<Corde> res = new ArrayList<>();

        for(int j = 0; j < nbSommets; j++) {
            if (Math.abs(idSommet - j) % (nbSommets - 1) > 1) {
                Corde c = new Corde(idSommet, j, this);
                res.add(c);
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "essaisSuccessifs.Polygone [nbSommets=" + nbSommets + ", p=" + p + "]";
    }




}