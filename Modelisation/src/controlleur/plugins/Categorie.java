/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur.plugins;

/**
 *
 * @author lalleaul
 */
public enum Categorie {

    TRAITEMENT("traitement"),
    TRANSFORMATION("transformation"),
    PREVISION("prévision");

    private String name;

    Categorie(String name) {
        this.name = name;
    }
    
    public String toString(){
        return this.name;
    }
    
    public boolean equals(Categorie autre){
        return this.name==autre.toString();
    }
}
