package ProgDynamique;

import java.util.ArrayList;






public class Main {

    public static void triang(Polygone poly, int indiceDepart, Triangulation[][] twoD_arr) {
        for (int i = indiceDepart + 1; i < (indiceDepart + poly.getNbSommets() - 1); i++) {
            if (i == indiceDepart + 1) {
                Corde cord = new Corde(poly.p.get(1), poly.p.get(poly.getNbSommets() - 1));
                if (twoD_arr[indiceDepart + 1][poly.getNbSommets() - 1] == null) {
                    ArrayList<Point> p2 = (ArrayList<Point>) poly.p.clone();
                    p2.remove(0);
                    Polygone poly2 = new Polygone(p2);
                    triang(poly2, indiceDepart + 1, twoD_arr);
                }
                Triangulation newTri = twoD_arr[indiceDepart + 1][poly.getNbSommets() - 1];
                newTri.addCorde(cord);
                twoD_arr[indiceDepart][poly.getNbSommets()] = newTri;
            } else if (i == indiceDepart + poly.getNbSommets() - 2) {
                Corde cord = new Corde(poly.p.get(0), poly.p.get(poly.getNbSommets() - 2));
                if (twoD_arr[indiceDepart][poly.getNbSommets() - 1] == null) {
                    ArrayList<Point> p2 = (ArrayList<Point>) poly.p.clone();
                    p2.remove(poly.getNbSommets() - 1);
                    Polygone poly2 = new Polygone(p2);
                    triang(poly2, indiceDepart, twoD_arr);
                }
                Triangulation newTri = twoD_arr[indiceDepart][poly.getNbSommets() - 1];
                newTri.addCorde(cord);
                if (twoD_arr[indiceDepart][poly.getNbSommets()].getLongueurTotale() > newTri.getLongueurTotale()) {
                    twoD_arr[indiceDepart][poly.getNbSommets()] = newTri;
                }
            } else {
                Corde cord1 = new Corde(poly.p.get(0), poly.p.get(i));
                Corde cord2 = new Corde(poly.p.get(i), poly.p.get(poly.getNbSommets() - 1));
                if (twoD_arr[indiceDepart][i + 1] == null) {
                    ArrayList<Point> p2 = (ArrayList<Point>) poly.p.clone();
                    p2.subList(0, i + 1);
                    Polygone poly2 = new Polygone(p2);
                    triang(poly2, indiceDepart, twoD_arr);
                }
                if (twoD_arr[indiceDepart + i][poly.getNbSommets() - i] == null) {
                    ArrayList<Point> p3 = (ArrayList<Point>) poly.p.clone();
                    p3.subList(i, poly.getNbSommets());
                    Polygone poly3 = new Polygone(p3);
                    triang(poly3, indiceDepart, twoD_arr);
                }
                Triangulation newTri = twoD_arr[indiceDepart + i][poly.getNbSommets() - i];
                ArrayList<Corde> temp = twoD_arr[indiceDepart][i + 1].getListeCordes();
                for (int k = 0; k < temp.size(); k++) {
                    newTri.addCorde(temp.get(k));
                }
                newTri.addCorde(cord1);
                newTri.addCorde(cord2);
                if (twoD_arr[indiceDepart][poly.getNbSommets()].getLongueurTotale() > newTri.getLongueurTotale()) {
                    twoD_arr[indiceDepart][poly.getNbSommets()] = newTri;
                }
            }
        }
    }

    public static Triangulation triangulationOpti(Polygone p) {
        Triangulation[][] twoD_arr = new Triangulation[p.getNbSommets()][p.getNbSommets()];
        triang(p, 0, twoD_arr);
        return twoD_arr[0][p.getNbSommets()];
    }

    public static void main(String[] args) {

        ArrayList<Point> pts = new ArrayList<>();
        pts.add(new Point(3, 1));
        pts.add(new Point(1, 4));
        pts.add(new Point(3, 7));
        pts.add(new Point(5, 4));

        Polygone p = new Polygone(pts);
        Triangulation tri = new Triangulation();

        tri = triangulationOpti(p);

    }

}