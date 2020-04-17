package essaisSuccessifs;

import java.util.ArrayList;
import java.util.List;

public class Polygone {

    private int nbSommets;
    private List<Point> p;
    private ArrayList<Corde> c;
    private Triangulation meilleureTriangulation;

    public Polygone(List<Point> p) {
        this.nbSommets = p.size();
        this.p = p;
        this.c = new ArrayList<>();
        this.meilleureTriangulation = null;
    }

    public ArrayList<Corde> getC() {
        return c;
    }

    public void setC(ArrayList<Corde> c) {
        this.c = c;
    }

    public int getNbSommets() {
        return nbSommets;
    }

    public void setNbSommets(int nbSommets) {
        this.nbSommets = nbSommets;
    }

    public void setP(ArrayList<Point> p) {
        this.p = p;
    }

    public Point getPoint (int id) { return p.get(id); }

    public ArrayList<Corde> cordesPossibles () {
        ArrayList<Corde> res = new ArrayList<>();
        for (int i = 0; i < nbSommets; i++) {
            for(int j = i; j < nbSommets; j++) {
                if (Math.abs(i - j) % (nbSommets - 1) > 1) {
                    Corde c = new Corde(i, j, this);
                    res.add(c);
                }
            }
        }
        return res;
    }


    public List<Point> getP() {
        return p;
    }

    public Triangulation trianguler (double lAct, double lMax) {

        // On retourne une triangulation vide si le polygone a moins de 3 côtés
        if (nbSommets <= 3) {
            return new Triangulation(this);
        }

        // On récupère la liste des cordes de ce polgone
        ArrayList<Corde> cordesPossibles = cordesPossibles();
        Triangulation res = new Triangulation(this);
        for (Corde c : cordesPossibles) {

            // Pour chaque corde, on l'ajoute à une triangulation
            Triangulation test = new Triangulation(this);
            test.addCorde(c);

            // Elagage : si on dépasse déjà la longueur maximale, on abandonne cette corde
            if (lMax != 0 && lAct + test.getLongueur() > lMax) {
                continue;
            }

            // On construit les 2 sous-polygones droit et gauche
            ArrayList<Point> pL = new ArrayList<>(this.p.subList(c.getP1(), c.getP2()));
            pL.add(p.get(c.getP2()));
            Polygone polyL = new Polygone(pL);

            ArrayList<Point> pR = new ArrayList<>(this.p.subList(0, c.getP1() + 1));
            pR.addAll(this.p.subList(c.getP2(), nbSommets));
            Polygone polyR = new Polygone(pR);

            // On triangule ces sous-polygones
            Triangulation tL = polyL.trianguler(test.getLongueur(), res.getLongueur());
            Triangulation tR = polyR.trianguler(test.getLongueur(), res.getLongueur());

            /*
            // Si l'un des polygones n'a pas pu être triangulisé, on abandonne la corde
            if (tR == null) {
                continue;
            }
            if (tL == null) {
                continue;
            }
*/
            // Si on a une collision entre les cordes des sous problèmes, on abandonne la corde
            test = test.fuse(tR, this);
            if (test == null) {
                continue;
            }
            test = test.fuse(tL, this);
            if (test == null) {
                continue;
            }

            // Si la trianguation est correcte et est meilleure que la référence, on change la référence
            if (test.isValide()) {
                if (res.isValide()) {

                    if (res.getLongueur() > test.getLongueur()) {
                        res = test;
                    }
                } else {
                    res = test;
                }
            }

        }


        return res;
    }

    @Override
    public String toString() {
        return "essaisSuccessifs.Polygone [nbSommets=" + nbSommets + ", p=" + p + "]";
    }




}