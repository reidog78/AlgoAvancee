package glouton;


import java.util.ArrayList;

public class Main {

    public static void main (String[] args) {

        ArrayList<Point> pts = new ArrayList<>();

        /* SOLUTION OPTIMALE
        pts.add(new Point(3, 0));
        pts.add(new Point(2, 1));
        pts.add(new Point(3, 2));
        pts.add(new Point(6, 3));
        pts.add(new Point(8, 2));
        pts.add(new Point(9, 1));
        pts.add(new Point(8, 0));
        pts.add(new Point(6, -1));
*/
        /* SOLUTION NON OPTIMALE
        pts.add(new Point(0, 5));
        pts.add(new Point(0, 10));
        pts.add(new Point(3, 14));
        pts.add(new Point(8, 16));
        pts.add(new Point(14, 16));
        pts.add(new Point(17, 13));
        pts.add(new Point(17, 8));
        pts.add(new Point(15, 5));
        pts.add(new Point(11, 2));
        pts.add(new Point(8, 0));
        pts.add(new Point(4, 0));
        pts.add(new Point(2, 2));
        */
/* SOLUTION NON OPTIMALE
        pts.add(new Point(0, 5));
        pts.add(new Point(0, 10));
        pts.add(new Point(3, 14));
        pts.add(new Point(8, 16));
        pts.add(new Point(14, 16));
        pts.add(new Point(8, 0));
        pts.add(new Point(4, 0));
        pts.add(new Point(2, 2));
    */
        Polygone p = new Polygone(pts);

        Triangulation t = p.trianguler();

        System.out.println(t);

    }

}