/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.plugins;

import model.SerieChro;

/**
 *
 * @author lalleaul
 */
public interface PluginTransformation extends PluginBase {

    /**
     * Obtient une nouvelle serie chronologie à partir de celle fournit en
     * paramètre
     *
     * @param serie
     * @return Nouvelle serie transformée
     */
    public SerieChro transform(SerieChro serie);
    
    
}
