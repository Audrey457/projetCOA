/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur.plugins.traitement;

import java.util.ArrayList;
import model.SerieChro;
import controlleur.plugins.Categorie;
import controlleur.plugins.PluginTraitement;

/**
 *
 * @author lalleaul
 */
public class PluginMoyenne implements PluginTraitement {

    private final String LIBELLE = "Moyenne";
    private final Categorie CATEGORIE = Categorie.TRAITEMENT;

    @Override
    public double getValue(SerieChro serie) throws Exception {
        double nbValeurs = serie.getColumnCount();
        if (nbValeurs == 0) {
            throw new Exception("Peut pas diviser par zéro");
        } else {
            double somme = 0;
            for (double valeur : serie.getCol2()) {
                somme += valeur;
            }
            return somme / nbValeurs;
        }
    }

    @Override
    public String getLibelle() {
        return this.LIBELLE;
    }

    @Override
    public Categorie getCategorie() {
        return this.CATEGORIE;
    }

    @Override
    public ArrayList<String> getParam() {
        return new ArrayList<String>();
    }

    @Override
    public void setParam(ArrayList<Double> listParam) {
    }

}
