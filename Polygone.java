import java.util.ArrayList;

public class Polygone {

    private int nbSommets;
    private ArrayList<Point> p;
    private ArrayList<Corde> c;

    public Polygone(ArrayList<Point> p) {
        this.nbSommets = p.size();
        this.p = p;
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

    public ArrayList<Corde> triangulerSuccessifs () {


        for (int i = 0; i < nbSommets; i++) {
            for (int j = i; j < nbSommets; j++) {
                boolean ok = true;
                if (!valideCorde(i, j)) {
                    System.out.println("Raté");
                    ok = false;
                }
                if (ok) {
                    c.add(new Corde(i, j));
                }
            }
        }

        return c;
    }

    public Boolean valideCorde(int s1, int s2, int s3, int s4) {
        Boolean returnValue = true;
        if (s2<s1){
            int temp = s1;
            s1 = s2;
            s2 = temp;
        }
        if (s4<s3){
            int temp = s3;
            s3 = s4;
            s4 = temp;
        }

        if(s1==s3 && s2==s4 || s1==s4 && s2==s3){
            returnValue = false;
        }
        else if(s1<s3 && s3<s2 && s2<s4){
            returnValue = false;
        }
        return returnValue;
    }

    @Override
    public String toString() {
        return "Polygone [nbSommets=" + nbSommets + ", p=" + p + "]";
    }
    


    
}