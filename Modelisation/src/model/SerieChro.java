package model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Modélise un tableau à 2 dimensions
 * Les entêtes ne font pas parties des données
 * 
 * @author utilisateur
 */
public class SerieChro extends AbstractTableModel implements Serializable {
    
    /**
     * Attributs
     */
    private String entete1;
    private String entete2;
    private ArrayList<String> col1;
    private ArrayList<Double> col2;

    /**
     * Setter & Getter
     */
    public String getEntete1() {
        return entete1;
    }

    public void setEntete1(String entete1) {
        this.entete1 = entete1;
    }

    public String getEntete2() {
        return entete2;
    }

    public void setEntete2(String entete2) {
        this.entete2 = entete2;
    }

    public ArrayList<String> getCol1() {
        return col1;
    }

    public void setCol1(ArrayList<String> col1) {
        this.col1 = col1;
    }

    public ArrayList<Double> getCol2() {
        return col2;
    }

    public void setCol2(ArrayList<Double> col2) {
        this.col2 = col2;
    }
    
    /**
     * Constructors
     */
    public SerieChro() {
        super();
        this.entete1 = "";
        this.entete2 = "";
        this.col1 = new ArrayList<String>();
        this.col2 = new ArrayList<Double>();
    }

    public SerieChro(String entete1, String entete2, ArrayList<String> col1, ArrayList<Double> col2) {
        super();
        this.entete1 = entete1;
        this.entete2 = entete2;
        this.col1 = col1;
        this.col2 = col2;
    }

    @Override
    public int getRowCount() {
        return this.col1.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            return this.col1.get(rowIndex);
        } else if (columnIndex == 2) {
            return this.col2.get(rowIndex);
        } else {
            return null;
        }
    }

    public void addValue(String date, Double value) {
        this.col1.add(date);
        this.col2.add(value);
    }

}
