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

    public Triangulation trianguler () {

        if (nbSommets <= 3) {
            return new Triangulation(this);
        }
        ArrayList<Corde> cordesPossibles = cordesPossibles();
        Triangulation res = new Triangulation(this);
        for (Corde c : cordesPossibles) {
            System.out.println("Polynome : " + this);
            System.out.println("Etepe : " + cordesPossibles.indexOf(c));
            System.out.println("Corde : " + c + " parmi " + cordesPossibles);
            Triangulation test = new Triangulation(this);
            test.addCorde(c);

            System.out.println("DBUG" + this.p);
            ArrayList<Point> pL = new ArrayList<>(this.p.subList(c.getP1(), c.getP2()));
            pL.add(p.get(c.getP2()));

            Polygone polyL = new Polygone(pL);
            System.out.println("L " + polyL);

            ArrayList<Point> pR = new ArrayList<>(this.p.subList(0, c.getP1() + 1));
            pR.addAll(this.p.subList(c.getP2(), nbSommets));
            Polygone polyR = new Polygone(pR);

            System.out.println("R " + polyR);

            Triangulation tL = polyL.trianguler();
            Triangulation tR = polyR.trianguler();

            boolean allok = true;

            if (tR == null) {
                System.out.println("Echec TR");
                allok = false;
            }

            if (tL == null) {
                System.out.println("Echec TL");
                allok = false;
            }

            if (allok) {
                test = test.fuse(tR, this);
                if (test == null) {
                    allok = false;
                } else {
                    test = test.fuse(tL, this);
                    if (test == null) {
                        allok = false;
                    }
                }

                if (allok) {

                    if (test.isValide()) {
                        if (res.isValide()) {

                            if (res.getLongueur() > test.getLongueur()) {
                                res = test;
                            } else {
                                System.out.println("TROPGRAN + ");
                            }
                        } else {
                            res = test;
                        }
                    } else {
                        System.out.println("PAVALID " + test);
                    }
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