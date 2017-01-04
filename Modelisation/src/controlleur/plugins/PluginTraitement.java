/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlleur.plugins;

import model.SerieChro;

/**
 *
 * @author lalleaul
 */
public interface PluginTraitement extends PluginBase{
    
    public double getValue(SerieChro serie)throws Exception;
}