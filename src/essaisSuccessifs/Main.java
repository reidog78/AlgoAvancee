package essaisSuccessifs;

import java.util.ArrayList;

public class Main {

    public static void main (String[] args) {

        ArrayList<Point> pts = new ArrayList<>();
        pts.add(new Point(3, 0));
        pts.add(new Point(2, 1));
        pts.add(new Point(3, 2));
        pts.add(new Point(6, 3));
        pts.add(new Point(8, 2));
        pts.add(new Point(9, 1));
        pts.add(new Point(8, 0));
        pts.add(new Point(6, -1));

        Polygone p = new Polygone(pts);

        Triangulation t = p.trianguler();

        System.out.println(t);

    }

}