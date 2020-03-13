public class Corde {

    private int s1;
    private int s2;

    public Corde(int s1, int s2) {
        if (s1 < s2) {
            this.s1 = s1;
            this.s2 = s2;
        } else {
            System.out.println("Tu fais de la merde " + s1 + " " + s2);
        }
    }

    public int getS1() {
        return s1;
    }

    public void setS1(int s1) {
        this.s1 = s1;
    }

    public int getS2() {
        return s2;
    }

    public void setS2(int s2) {
        this.s2 = s2;
    }

    

}