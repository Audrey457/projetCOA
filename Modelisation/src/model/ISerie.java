/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

public interface ISerie {
    public ArrayList<Ligne> getEnsLignes();
    public void modifierValeur(double valeur, int ligne);
    public String[] getEntetes();
    public void setEnsLignes(ArrayList<Ligne> ensLignes);
    public ArrayList<Double> getAllValues();
}
