package controlleur;

import java.util.ArrayList;

/**
 *
 * @author gianni
 */

public class operation
{
   
    //Transformation logarithme
    
    public static double log(double a)
    {
        return Math.log(a);    
    }
    
    //Transformation Box-cox
    
    public static double BoxCox( double x, double alpha)
    {
        
        if(alpha > 0)
        {
        return (Math.pow(x, alpha) -1) / alpha;
        }
        else if(alpha == 0)
        {
        return Math.log(x);
        }
         throw new IllegalArgumentException();
    }
    
    //Transformation logistique pour les données Intervalle ]0,1[
    
    public static double logistique(double x)
    {
         if( x<1 && x>0)
        {
            return Math.log(x/(1-x)); 
        }
        return x;
    }
    
    
}

