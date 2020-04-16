package essaisSuccessifs;

import java.util.ArrayList;

public class Main {
        
    public static void main (String[] args) {

        ArrayList<Point> pts = new ArrayList<>();
        pts.add(new Point(0, 0));
        pts.add(new Point(0, 10));
        pts.add(new Point(10, 10));
        pts.add(new Point(15, 5));
        pts.add(new Point(12, 3));
        pts.add(new Point(10, 0));

        Polygone p = new Polygone(pts);

        System.out.println(p.triangulerSuccessifs());

    }

}