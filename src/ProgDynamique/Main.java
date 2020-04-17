package ProgDynamique;

import java.util.ArrayList;


public class Main {

    public static void triang(Polygone poly, int indiceDepart, Triangulation[][] twoD_arr) {            // poly le polygone à trianguler (liste de points), indice départ l'indice du premier sommet de p dans le polygone de départ, twoD_arr le tableau des solutions stockées.
        if (poly.getNbSommets()<=3){                                                                    // Cas où poly est un triangle
            twoD_arr[indiceDepart][poly.getNbSommets()-1]= new Triangulation();
        }
        else{
            int compteur = 0;                                                                           // compteur permet de bien guarder le numéro de l'étape que l'on est en train de réaliser, quelque soit l'appel récursif et indiceDepart
            for (int i = indiceDepart + 1; i < (indiceDepart + poly.getNbSommets() - 1); i++) {
                compteur+=1;
                if (i == indiceDepart + 1) {                                                            // Traitement du sous problème où on choisi le somment s1
                    Corde cord = new Corde(poly.p.get(1), poly.p.get(poly.getNbSommets() - 1));
                    if (twoD_arr[indiceDepart + 1][poly.getNbSommets() - 2] == null) {                  // On vérifie que le sous problème dont on a besoin a une solution, et on la crée le cas échéant
                        twoD_arr[indiceDepart + 1][poly.getNbSommets() - 2] = new Triangulation();
                        ArrayList<Point> p2 = (ArrayList<Point>) poly.p.clone();
                        p2.remove(0);
                        Polygone poly2 = new Polygone(p2);
                        triang(poly2, indiceDepart + 1, twoD_arr);
                    }
                    Triangulation newTri = new Triangulation(twoD_arr[indiceDepart + 1][poly.getNbSommets() - 2]);  //création de la triangulation à partir de la sous-solution
                    newTri.addCorde(cord);
                    twoD_arr[indiceDepart][poly.getNbSommets()-1] = newTri;
                } else if (i == indiceDepart + poly.getNbSommets() - 2) {                               // Traitement du sous problème où on choisi le somment si+t-2
                    Corde cord = new Corde(poly.p.get(0), poly.p.get(poly.getNbSommets() - 2));
                    if (twoD_arr[indiceDepart][poly.getNbSommets() - 2] == null) {
                        twoD_arr[indiceDepart][poly.getNbSommets() - 2] = new Triangulation();
                        ArrayList<Point> p2 = (ArrayList<Point>) poly.p.clone();
                        p2.remove(poly.getNbSommets() - 1);
                        Polygone poly2 = new Polygone(p2);
                        triang(poly2, indiceDepart, twoD_arr);
                    }
                    Triangulation newTri = new Triangulation(twoD_arr[indiceDepart][poly.getNbSommets() - 2]);
                    newTri.addCorde(cord);
                    if (twoD_arr[indiceDepart][poly.getNbSommets()-1].getLongueurTotale() > newTri.getLongueurTotale()) {   // comparaison pour tester l'amélioration de la solution
                        twoD_arr[indiceDepart][poly.getNbSommets()-1] = newTri;
                    }
                } else {                                                                                // Traitement des sous-problèmes où on choisi un des sommets s2 à si+t-3
                    Corde cord1 = new Corde(poly.p.get(0), poly.p.get(compteur));
                    Corde cord2 = new Corde(poly.p.get(compteur), poly.p.get(poly.getNbSommets() - 1));
                    if (twoD_arr[indiceDepart][compteur] == null) {
                        twoD_arr[indiceDepart][compteur] = new Triangulation();
                        ArrayList<Point> p2 = (ArrayList<Point>) poly.p.clone();
                        Polygone poly2 = new Polygone(new ArrayList<>(p2.subList(0, compteur + 1)));
                        triang(poly2, indiceDepart, twoD_arr);
                    }
                    if (twoD_arr[indiceDepart + compteur][poly.getNbSommets()-compteur-1] == null) {
                        twoD_arr[indiceDepart + compteur][poly.getNbSommets()-compteur-1] = new Triangulation();
                        ArrayList<Point> p3 = (ArrayList<Point>) poly.p.clone();
                        Polygone poly3 = new Polygone(new ArrayList<>(p3.subList(compteur, poly.getNbSommets())));
                        triang(poly3, indiceDepart +compteur, twoD_arr);
                    }
                    Triangulation newTri = new Triangulation(twoD_arr[indiceDepart + compteur][poly.getNbSommets()-compteur-1]);
                    ArrayList<Corde> temp = twoD_arr[indiceDepart][compteur].getListeCordes();
                    for (int k = 0; k < temp.size(); k++) {
                        newTri.addCorde(temp.get(k));
                    }
                    newTri.addCorde(cord1);
                    newTri.addCorde(cord2);
                    if (twoD_arr[indiceDepart][poly.getNbSommets()-1].getLongueurTotale() > newTri.getLongueurTotale()) {   // comparaison pour tester l'amélioration de la solution
                        twoD_arr[indiceDepart][poly.getNbSommets()-1] = newTri;
                    }
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

        pts.add(new Point(0, 5));  // liste de 12 points pour un polygone convexe. 
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

        /*pts.add(new Point(3, 0));  //liste de 8 points pour un polygone convexe (commenter celui d'avant pour utiliser)
        pts.add(new Point(2, 1));
        pts.add(new Point(3, 2));
        pts.add(new Point(6, 3));
        pts.add(new Point(8, 2));
        pts.add(new Point(9, 1));
        pts.add(new Point(8, 0));        // il est laissé libre la possibilité de tester l'algorithme avec n'importe quel polygone convexe
        pts.add(new Point(6, -1));*/     // Nous n'avons malheureusement pas eu le temps d'implémenter un générateur de polygone convexe aléatoire...

        Polygone p = new Polygone(pts);
        Triangulation tri = new Triangulation();

        tri = triangulationOpti(p);
        
        System.out.println(tri.toString());

    }

}