/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.plugins;

import java.util.ArrayList;

/**
 *
 * @author lalleaul
 */
public interface PluginBase {

    /**
     * Obtient le libellé à afficher
     *
     * @return Le libellé sous forme de String.
     */
    public String getLibelle();

    /**
     * Obtient la catégorie du plugin : transformation, traitement, prévision...
     *
     * @return La catégorie du plugin
     */
    public Categorie getCategorie();

    /**
     * Obtient une liste des libellés des paramètres demandés
     * 
     * WARNING : liste static, comment faire pour le lissage par exemple?
     * 
     * @return Liste de string des libellés de paramètres
     */
    public ArrayList<String> getParam();

    /**
     * Fixe les paramètres de la transformation
     *
     * @param parametre
     */
    public void setParam(ArrayList<Double> listParam);

}
