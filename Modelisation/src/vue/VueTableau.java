/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import model.SerieChro;
import model.SerieChro2;

public class VueTableau extends JTable {
    
    SerieChro2 tab;
    public VueTableau(TableModel tab){
        super(tab);
        this.tab = (SerieChro2)tab;
    }
}
