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

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
}
