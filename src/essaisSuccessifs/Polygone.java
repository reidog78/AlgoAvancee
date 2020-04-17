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

    /*public Triangulation trianguler (double lact, double lmax) {
        System.out.println("Triangulation de " + this + " avec " + lact + " " + lmax);
        if (this.nbSommets == 3) {
            return new Triangulation(this);
        }

        Triangulation t = new Triangulation(this);

        ArrayList<Corde> cordesPossible = cordesPossibles();
        System.out.println(this);
        Corde choisie = cordesPossible.get((int) Math.floor(Math.random() * cordesPossible.size()));
        t.addCorde(choisie);
        System.out.println("Corde choisie : " + choisie);

        if (t.getLongueur() > lmax && lmax != -1) {
            return null;
        }

        ArrayList<Point> pLeft = new ArrayList<>();
        ArrayList<Point> pRight = new ArrayList<>();

        pLeft.add(p.get(choisie.getP1()));
        pLeft.add(p.get(choisie.getP2()));
        pRight.add(p.get(choisie.getP1()));
        pRight.add(p.get(choisie.getP2()));

        ArrayList<Point> current = pRight;

        for (int k = 0; k < this.nbSommets; k++) {
            if (k != choisie.getP1() && k != choisie.getP2()) {
                current.add(p.get(k));
            } else {
                current = current == pRight ? pLeft : pRight;
            }
        }

        Polygone left = new Polygone(pLeft);
        Polygone right = new Polygone(pRight);
        System.out.println("Right : " + right);

        Triangulation tLeft = left.trianguler(lact + choisie.longueur(), lmax);
        if (tLeft == null) {
            return null;
        }
        Triangulation tRight = right.trianguler(lact + choisie.longueur(), lmax);
        if (tRight == null) {
            return null;
        }

        System.out.println("Fuion" + t + " " + tLeft + " " + tRight);
        t = t.fuse()
        System.out.println("Resultat : " + t);

        return t;
    }


    public Triangulation triangulerMin () {
        Triangulation res = new Triangulation(this);
        double max = -1;
        for (int i = 0; i < 10; i++) {

            Triangulation test = trianguler(0, max);

            if (test != null) {
                if (max != -1) {
                    if (test.getLongueur() < max) {
                        res = test;
                        max = test.getLongueur();
                        System.out.println("Réussi");
                    } else {
                        System.out.println("Raté");
                    }
                } else {
                    res = test;
                    max = test.getLongueur();
                    System.out.println("changement de max");
                }
            } else {
                System.out.println("nul");
            }
        }


        return res;
    }
*/

    public Triangulation trianguler (ArrayList<Corde> cordesPossibles) {

        if (nbSommets <= 3) {
            return new Triangulation(this);
        }

        if (cordesPossibles.size() == 0) {
            return null;
        }

        Triangulation res = new Triangulation(this);
        System.out.println("Polynome : " + this);
        for (Corde c : cordesPossibles) {
            System.out.println("Etepe : " + cordesPossibles.indexOf(c));
            System.out.println("Corde : " + c + " parmi " + cordesPossibles);
            Triangulation test = new Triangulation(this);
            test.addCorde(c);
            ArrayList<Corde> nllesCordesPossibles = new ArrayList<>(cordesPossibles.subList(cordesPossibles.indexOf(c) + 1, cordesPossibles.size()));

            System.out.println("DBUG" + this.p);
            ArrayList<Point> pL = new ArrayList<>(this.p.subList(c.getP1(), c.getP2()));
            pL.add(p.get(c.getP2()));

            Polygone polyL = new Polygone(pL);
            System.out.println("L " + polyL);

            ArrayList<Point> pR = new ArrayList<>(this.p.subList(0, c.getP1() + 1));
            pR.addAll(this.p.subList(c.getP2(), nbSommets));
            Polygone polyR = new Polygone(pR);

            System.out.println("R " + polyR);

            Triangulation tR = polyR.trianguler(nllesCordesPossibles);

            boolean allok = true;

            if (tR == null) {
                System.out.println("Echec TR");
                allok = false;
            }

            Triangulation tL = polyL.trianguler(nllesCordesPossibles);
            if (tL == null) {
                System.out.println("Echec TL");
                allok = false;
            }

            if (allok) {
                test = test.fuse(tR, this);
                if (test == null) {
                    allok = false;
                }
                test = test.fuse(tL, this);
                if (test == null) {
                    allok = false;
                }

                if (allok) {

                    if (test.isValide()) {
                        res = test;
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