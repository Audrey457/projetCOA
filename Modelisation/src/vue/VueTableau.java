/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controlleur.Controleur;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import model.SerieChro;

/**
 *
 * @author utilisateur
 */
public class VueTableau extends JTable {
    
    SerieChro tab2;
    public VueTableau(TableModel tab2){
        super(tab2);
        this.tab2 = (SerieChro)tab2;
    }
    
}
