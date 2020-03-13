import java.util.ArrayList;

public class Polygone {

    private int nbSommets;
    private ArrayList<Point> p;
    private ArrayList<Corde> c;

    public Polygone(ArrayList<Point> p) {
        this.nbSommets = p.size();
        this.p = p;
        this.c = new ArrayList<>();
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
                    System.out.println("Raté " + i + " " + j);
                    ok = false;
                }
                if (ok) {
                    c.add(new Corde(i, j));
                }
            }
        }

        return c;
    }

    public Boolean valideCorde(int s1, int s2) {
        Boolean returnValue = true;
        
        if (s2<s1){
            int temp = s1;
            s1 = s2;
            s2 = temp;
        }
        if(s1==s2){
            returnValue = false;
        }

        if(s1==(s2-1)%nbSommets || s1 == (s2+1)%nbSommets){
            returnValue = false;
        }
        for (int i=0 ; i<c.size(); i++){
            System.out.println(s1 + " " + s2 + " " + c.get(i));
            if(s1==c.get(i).getS1() && s2==c.get(i).getS2() || s1==c.get(i).getS2() && s2==c.get(i).getS1()){
                returnValue = false;
            }
            else if(s1<c.get(i).getS1() && c.get(i).getS1()<s2 && s2<c.get(i).getS2()){
                returnValue = false;
            }
            else if(s1>c.get(i).getS1() && c.get(i).getS2()>s1 && s2>c.get(i).getS2()){
                returnValue = false;
            }
            
        }

        System.out.println(returnValue);

        return returnValue;
    }

    @Override
    public String toString() {
        return "Polygone [nbSommets=" + nbSommets + ", p=" + p + "]";
    }
    


    
}