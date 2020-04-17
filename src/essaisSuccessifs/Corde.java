package essaisSuccessifs;

import static java.lang.Integer.min;
import static java.lang.Integer.max;

public class Corde {

    private int p1;
    private int p2;
    private double l;

    public Corde(int p1, int p2, Polygone p) {

        this.p1 = min(p1, p2);
        this.p2 = max(p1, p2);

        Point pt1 = p.getPoint(this.p1);
        Point pt2 = p.getPoint(this.p2);

        this.l = pt1.distance(pt2);
    }

    public double longueur () {
        return l;
    }

    @Override
    public String toString() {
        return "Corde{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                //", l=" + l +
                '}';
    }

    public int getP1() {
        return p1;
    }

    public int getP2() {
        return p2;
    }

    public boolean intersection (Corde c) {
        // Si les points des 2 cordes ne sont pas entrelacés, il n'y a pas d'intersection
        if (c.getP1() <= p1 && p1 < p2 && p2 <= c.getP2()) {
            return false;
        } else if (p1 < p2 && p2 <= c.getP1() && c.getP1() < c.getP2()) {
            return false;
        } else if (p2 <= c.getP1() && c.getP1() < c.getP2() && c.getP2() <= p1) {
            return false;
        } else if (c.getP1() < c.getP2() && c.getP2() <= p1 && p1 < p2){
            return false;
        }
        return true;
    }

}