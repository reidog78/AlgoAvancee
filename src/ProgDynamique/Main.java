package ProgDynamique;

import java.util.ArrayList;






public class Main {

    public static void triang(Polygone poly, int indiceDepart, Triangulation[][] twoD_arr) {
        if (poly.getNbSommets()<=3){
            twoD_arr[indiceDepart][poly.getNbSommets()-1]= new Triangulation();
        }
        else{
            for (int i = indiceDepart + 1; i < (indiceDepart + poly.getNbSommets() - 1); i++) {
                if (i == indiceDepart + 1) {
                    System.out.println(poly.toString());
                    Corde cord = new Corde(poly.p.get(1), poly.p.get(poly.getNbSommets() - 1));
                    System.out.println(poly.getNbSommets() +" 1er essai "+cord.toString());
                    if (twoD_arr[indiceDepart + 1][poly.getNbSommets() - 2] == null) {
                        twoD_arr[indiceDepart + 1][poly.getNbSommets() - 2] = new Triangulation();
                        ArrayList<Point> p2 = (ArrayList<Point>) poly.p.clone();
                        p2.remove(0);
                        Polygone poly2 = new Polygone(p2);
                        triang(poly2, indiceDepart + 1, twoD_arr);
                    }
                    Triangulation newTri = new Triangulation(twoD_arr[indiceDepart + 1][poly.getNbSommets() - 2]);
                    newTri.addCorde(cord);
                    twoD_arr[indiceDepart][poly.getNbSommets()-1] = newTri;
                    System.out.println(poly.getNbSommets()+" : "+twoD_arr[indiceDepart][poly.getNbSommets()-1]);
                } else if (i == indiceDepart + poly.getNbSommets() - 2) {
                    Corde cord = new Corde(poly.p.get(0), poly.p.get(poly.getNbSommets() - 2));
                    System.out.println(poly.getNbSommets() +" 2eme essai "+cord.toString());
                    if (twoD_arr[indiceDepart][poly.getNbSommets() - 2] == null) {
                        twoD_arr[indiceDepart][poly.getNbSommets() - 2] = new Triangulation();
                        ArrayList<Point> p2 = (ArrayList<Point>) poly.p.clone();
                        p2.remove(poly.getNbSommets() - 1);
                        Polygone poly2 = new Polygone(p2);
                        triang(poly2, indiceDepart, twoD_arr);
                    }
                    Triangulation newTri = new Triangulation(twoD_arr[indiceDepart][poly.getNbSommets() - 2]);
                    System.out.println("DEBUG "+twoD_arr[indiceDepart][poly.getNbSommets() - 2]);
                    newTri.addCorde(cord);
                    System.out.println("DEBUG "+twoD_arr[indiceDepart][poly.getNbSommets() - 2]);
                    if (twoD_arr[indiceDepart][poly.getNbSommets()-1].getLongueurTotale() > newTri.getLongueurTotale()) {
                        twoD_arr[indiceDepart][poly.getNbSommets()-1] = newTri;
                    }
                    System.out.println(poly.getNbSommets()+" : "+twoD_arr[indiceDepart][poly.getNbSommets()-1]);
                } else {
                    Corde cord1 = new Corde(poly.p.get(0), poly.p.get(i));
                    Corde cord2 = new Corde(poly.p.get(i), poly.p.get(poly.getNbSommets() - 1));
                    if (twoD_arr[indiceDepart][i] == null) {
                        twoD_arr[indiceDepart][i] = new Triangulation();
                        ArrayList<Point> p2 = (ArrayList<Point>) poly.p.clone();
                        Polygone poly2 = new Polygone(new ArrayList<>(p2.subList(0, i + 1)));
                        triang(poly2, indiceDepart, twoD_arr);
                    }
                    if (twoD_arr[indiceDepart + i][poly.getNbSommets()-i-1] == null) {
                        twoD_arr[indiceDepart + i][poly.getNbSommets()-i-1] = new Triangulation();
                        ArrayList<Point> p3 = (ArrayList<Point>) poly.p.clone();
                        Polygone poly3 = new Polygone(new ArrayList<>(p3.subList(i, poly.getNbSommets())));
                        triang(poly3, indiceDepart, twoD_arr);
                    }
                    Triangulation newTri = new Triangulation(twoD_arr[indiceDepart + i][poly.getNbSommets()-i-1]);
                    ArrayList<Corde> temp = twoD_arr[indiceDepart][i].getListeCordes();
                    for (int k = 0; k < temp.size(); k++) {
                        newTri.addCorde(temp.get(k));
                    }
                    newTri.addCorde(cord1);
                    newTri.addCorde(cord2);
                    if (twoD_arr[indiceDepart][poly.getNbSommets()-1].getLongueurTotale() > newTri.getLongueurTotale()) {
                        twoD_arr[indiceDepart][poly.getNbSommets()-1] = newTri;
                    }
                    System.out.println(poly.getNbSommets()+" : "+twoD_arr[indiceDepart][poly.getNbSommets()-1]);
                }
            }
        }
    }

    public static Triangulation triangulationOpti(Polygone p) {
        Triangulation[][] twoD_arr = new Triangulation[p.getNbSommets()][p.getNbSommets()];
        triang(p, 0, twoD_arr);
        return twoD_arr[0][p.getNbSommets()-1];
    }

    public static void main(String[] args) {

        ArrayList<Point> pts = new ArrayList<>();
        pts.add(new Point(3, 0));
        pts.add(new Point(0, 1));
        pts.add(new Point(3, 2));
        pts.add(new Point(8, 2));
        pts.add(new Point(11, 1));
        pts.add(new Point(8, 0));

        Polygone p = new Polygone(pts);
        Triangulation tri = new Triangulation();

        tri = triangulationOpti(p);
        
        System.out.println(tri.toString());

    }

}