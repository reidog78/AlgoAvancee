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

    public ArrayList<Integer[]> triangulerSuccessifs () {

        ArrayList<Integer[]> triangulation = new ArrayList<>();

        for (int i = 0; i < nbSommets; i++) {
            for (int j = 0; j < nbSommets; j++) {
                boolean ok = true;
                for (int k = 0; k < triangulation.size(); k++) {
                    Integer[] corde = triangulation.get(k);
                    System.out.println(i + " " + j + " " + corde[0] + " " + corde[1]);
                    if (!valideCorde(i, j, corde[0], corde[1])) {
                        System.out.println("Raté");
                        ok = false;
                    }
                }
                if (ok) {
                    Integer[] l = {0, 0};
                    l[0] = i;
                    l[1] = j;
                    System.out.println(l[0] + " " + l[1]);
                    triangulation.add(l);
                }
            }
        }

        return triangulation;
    }

    public Boolean valideCorde(int s1, int s2) {
        Boolean returnValue = true;
        
        if (s2<s1){
            int temp = s1;
            s1 = s2;
            s2 = temp;
        }
        for (int i=0 ; i<c.size(); i++){

            if(s1==c.get(i).getS1() && s2==c.get(i).getS2() || s1==c.get(i).getS2() && s2==c.get(i).getS1()){
                returnValue = false;
            }
            else if(s1<c.get(i).getS1() && c.get(i).getS1()<s2 && s2<c.get(i).getS2()){
                returnValue = false;
            }
            
        }
        return returnValue;
    }

    @Override
    public String toString() {
        return "Polygone [nbSommets=" + nbSommets + ", p=" + p + "]";
    }
    


    
}