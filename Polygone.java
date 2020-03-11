import java.lang.reflect.Array;
import java.util.ArrayList;

public class Polygone {

    private int nbSommets;
    private Point[] p;

    public Polygone(Point[] p) {
        this.nbSommets = p.length;
        this.p = p;
    }

    public int getNbSommets() {
        return nbSommets;
    }

    public void setNbSommets(int nbSommets) {
        this.nbSommets = nbSommets;
    }

    public void triangulerSuccessifs () {

        ArrayList<Integer[]> triangulation = new ArrayList<>();

        for (int i = 0; i < nbSommets; i++) {
            for (int j = 0; j < nbSommets; i++) {
                for (int k = 0; k < triangulation.size(); k++)
                if (valideCorde(i, j, triangulation.get(k)[0], triangulation.get(k)[1])) {
                    Integer[] l;
                    l[0] = i;
                    l[1] = j;
                    triangulation.add(l);
                }
            }
        }
    }

    public void setP(Point[] p) {
        this.p = p;
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
    


}