package ProgDynamique;

public class Corde {

    private Point s1;
    private Point s2;
    private double length;

    public Corde(Point s1, Point s2) {
            this.s1 = s1;
            this.s2 = s2;
            this.length = Math.sqrt( (s2.getX() - s1.getX() ) * (s2.getX() - s1.getX() ) + (s2.getY() - s1.getY() ) * ( s2.getY() - s1.getY() ) );
    }

    public Point getS1() {
        return s1;
    }

    public void setS1(Point s1) {
        this.s1 = s1;
    }

    public Point getS2() {
        return s2;
    }

    public void setS2(Point s2) {
        this.s2 = s2;
    }

    @Override
    public String toString() {
        return "progDynamique.Corde [s1=" + s1 + ", s2=" + s2 + "]";
    }

    public double getLength() {
        return length;
    }


    

}