/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur.plugins;

import java.util.ArrayList;
import javax.swing.JFrame;
import model.ISerie;

/**
 *
 * @author lalleaul
 */
public interface PluginBase {
   
    /**
     * Obtient le libellé à afficher
     *
     * @return String
     */
    public String getLibelle();

    /**
     * Obtient la catégorie du plugin : transformation, traitement, prévision...
     *
     * @return Categorie
     */
    public Categorie getCategorie();
    
    public void setSerie(ISerie serie);
    
    public void askValues(JFrame frame);
    
    public boolean isPossible();

}
