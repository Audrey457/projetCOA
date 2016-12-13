/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author utilisateur
 */
public class TableauModele extends AbstractTableModel {
    int [][] donnees;
    int nbColonnes;
    int nbLignes;
    
    public TableauModele(int [][] donnees, int nbColonnes, int nbLignes){
        super();
        this.donnees = donnees;
        this.nbColonnes = nbColonnes;
        this.nbLignes = nbLignes;
    }

    @Override
    public int getRowCount() {
        return nbLignes;
    }

    @Override
    public int getColumnCount() {
        return nbColonnes;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return donnees[columnIndex][rowIndex];
    }
    
}
