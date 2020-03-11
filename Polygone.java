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

    public Point[] getP() {
        return p;
    }

    public void setP(Point[] p) {
        this.p = p;
    }

    


}