package glouton;


public class Corde {

    private Point p1;
    private Point p2;
    private double l;

    public Corde(Point p1, Point p2, Polygone p) {

        this.p1 = p1;
        this.p2 = p2;


        this.l = p1.distance(p2);
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

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }


}