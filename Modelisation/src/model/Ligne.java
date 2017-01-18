package model;
public class Ligne {
    private String valDate;
    private Double valeur;
    
    public Ligne(){
        valDate = "";
        valeur = null;
    }

    public Ligne(String valDate, Double valeur) {
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
