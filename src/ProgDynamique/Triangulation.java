package ProgDynamique;

import java.util.ArrayList;

public class Triangulation {

    private ArrayList<Corde> listeCordes;
    private double longueurTotale;

    public Triangulation(){
        this.listeCordes = new ArrayList<Corde>();
        this.longueurTotale = 0;
    }

    public Triangulation(ArrayList<Corde> listeCordes){
        this.listeCordes = listeCordes;
        double sum = 0;
        for (int i=0; i<listeCordes.size();i++){
            sum = sum + listeCordes.get(i).getLength();
        }
        this.longueurTotale = sum;
    }

    public Triangulation(Triangulation t){
        this.listeCordes = (ArrayList<Corde>) t.listeCordes.clone();
        this.longueurTotale = t.longueurTotale;
    }

    public void addCorde(Corde c){
        this.listeCordes.add(c);
        this.longueurTotale = longueurTotale + c.getLength();
    }

    public ArrayList<Corde> getListeCordes() {
        return listeCordes;
    }

    public void setListeCordes(ArrayList<Corde> listeCordes) {
        this.listeCordes = listeCordes;
    }

    public double getLongueurTotale() {
        return longueurTotale;
    }

    public void setLongueurTotale(double longueurTotale) {
        this.longueurTotale = longueurTotale;
    }

    @Override
    public String toString() {
        return "Triangulation [listeCordes=" + listeCordes + ", longueurTotale=" + longueurTotale + "]";
        //return " "+listeCordes.size();
    }

}