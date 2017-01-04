/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author lorietta
 */
public class SerieToUse extends Observable {
    SerieChro2 serieEncap;
    
    public SerieToUse(){
        serieEncap = new SerieChro2();
    }
    
    public SerieToUse(ArrayList<Ligne> ensLignes){
        serieEncap = new SerieChro2(ensLignes);
    }
    
    public ArrayList<Ligne> getEnsLignes(){
        return serieEncap.getEnsLignes();
    }
    
    public void modifierValeur(double valeur, int ligne){
        serieEncap.modifierValeur(valeur, ligne);
    }

    public String[] getEntetes() {
        return serieEncap.getEntetes();
    }

    public void setEnsLignes(ArrayList<Ligne> ensLignes) {
        serieEncap.setEnsLignes(ensLignes);
    }
    
    public int getRowCount() {
        return serieEncap.getRowCount();
    }

    public int getColumnCount() {
        return serieEncap.getColumnCount(); 
    }
    
    public String getColumnName(int columnIndex){
        return serieEncap.getColumnName(columnIndex);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return serieEncap.getValueAt(rowIndex, columnIndex);
    }  

    public ArrayList<Double> getAllValues() {
        return serieEncap.getAllValues();
    }
}
