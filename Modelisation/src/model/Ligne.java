package model;
public class Ligne {
    private String valDate;
    private double valeur;
    
    public Ligne(){
        valDate = "";
        valeur = 0.0;
    }

    public Ligne(String valDate, double valeur) {
        this.valDate = valDate;
        this.valeur = valeur;
    }

    public String getValDate() {
        return valDate;
    }

    public void setValDate(String valDate) {
        this.valDate = valDate;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
}
