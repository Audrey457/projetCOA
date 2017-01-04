/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Observable;
import javax.swing.event.TableModelListener;


public class SerieToUse extends Observable implements ISerie{

    private final String[] entetes = {"Date", "Valeur"};
    private ArrayList<Ligne> ensLignes;
    
    public SerieToUse(){
        ensLignes = new ArrayList<>();
    }
    
    public SerieToUse(ArrayList<Ligne> ensLignes){
        this.ensLignes = ensLignes;
    }
    
    public ArrayList<Ligne> getEnsLignes(){
        return ensLignes;
    }
    
    public void modifierValeur(double valeur, int ligne){
        Ligne temp = new Ligne(ensLignes.get(ligne).getValDate(), valeur);
        ensLignes.set(ligne, temp);
    }

    public String[] getEntetes() {
        return entetes;
    }

    public void setEnsLignes(ArrayList<Ligne> ensLignes) {
        this.ensLignes = ensLignes;
    }
    
    @Override
    public int getRowCount() {
        return this.ensLignes.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length; 
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return entetes[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return this.ensLignes.get(rowIndex).getValDate();
            case 1:
                return this.ensLignes.get(rowIndex).getValeur();
            default:
                throw new IllegalArgumentException();
        }
    }  

    //Pour recuperer la 2ème colonne du tableau
    @Override
    public ArrayList<Double> getAllValues() {
        ArrayList<Double> resultat = new ArrayList<>();
        for(Ligne l: this.ensLignes){
            resultat.add(l.getValeur());
        }
        return resultat;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCellEditable(int arg0, int arg1) {
        return false;
    }

    @Override
    public void setValueAt(Object arg0, int arg1, int arg2) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
