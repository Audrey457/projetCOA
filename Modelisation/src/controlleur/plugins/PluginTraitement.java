package controlleur.plugins;

import model.ISerie;

/**
 *
 * @author lalleaul
 */
public interface PluginTraitement extends PluginBase{
    
    /**
     * Obtient la valeur calculée par le traitement
     * 
     * @param serie
     * @return double
     * @throws Exception 
     */
    public double getValue()throws Exception;
    
}