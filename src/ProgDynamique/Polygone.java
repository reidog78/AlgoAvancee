package ProgDynamique;

import java.util.ArrayList;

public class Polygone {

    private int nbSommets;
    public ArrayList<Point> p;

    public Polygone(ArrayList<Point> p) {
        this.nbSommets = p.size();
        this.p = p;
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

    

    @Override
    public String toString() {
        return "progDynamique.Polygone [nbSommets=" + nbSommets + ", p=" + p + "]";
    }
}  

    