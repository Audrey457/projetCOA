/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class SerieChro2 extends AbstractTableModel implements ISerie{

    private final String[] entetes = {"Date", "Valeur"};
    private ArrayList<Ligne> ensLignes;
    
    public SerieChro2(){
        ensLignes = new ArrayList<>();
    }
    
    public SerieChro2(ArrayList<Ligne> ensLignes){
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
}
