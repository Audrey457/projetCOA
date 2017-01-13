/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class SerieToUse extends Observable implements ISerie {

	private String[] entetes = { "Date", "Valeur" };
	private ArrayList<Ligne> ensLignes;
	protected EventListenerList listenerList = new EventListenerList();

	public SerieToUse() {
		ensLignes = new ArrayList<>();
	}

	public SerieToUse(ArrayList<Ligne> ensLignes) {
		this.ensLignes = ensLignes;
	}

	public void setSerieToUse(SerieToUse importSerie) {
		this.ensLignes = importSerie.ensLignes;
		this.entetes = importSerie.entetes;
		this.setChanged();
		this.notifyObservers("serieChange");
	}

	public ArrayList<Ligne> getEnsLignes() {
		return ensLignes;
	}

	public void modifierValeur(double valeur, int ligne) {
		Ligne temp = new Ligne(ensLignes.get(ligne).getValDate(), valeur);
		ensLignes.set(ligne, temp);
		this.setChanged();
		this.notifyObservers("serieChange");
	}

	public String[] getEntetes() {
		return entetes;
	}

	public void setEnsLignes(ArrayList<Ligne> ensLignes) {
		this.ensLignes = ensLignes;
		this.setChanged();
		this.notifyObservers("serieChange");
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
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public void setColumnName(int columnIndex, String columnName) {
		entetes[columnIndex] = columnName;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return this.ensLignes.get(rowIndex).getValDate();
		case 1:
			return this.ensLignes.get(rowIndex).getValeur();
		default:
			throw new IllegalArgumentException();
		}
	}

	// Pour recuperer la 2ème colonne du tableau
	@Override
	public ArrayList<Double> getAllValues() {
		ArrayList<Double> resultat = new ArrayList<>();
		for (Ligne l : this.ensLignes) {
			resultat.add(l.getValeur());
		}
		return resultat;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return Object.class;
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		listenerList.add(TableModelListener.class, l);
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		listenerList.remove(TableModelListener.class, l);
	}

	public void fireTableChanged(TableModelEvent event) {
		int index;
		TableModelListener listener;
		Object[] list = listenerList.getListenerList();

		for (index = 0; index < list.length; index += 2) {
			listener = (TableModelListener) list[index + 1];
			listener.tableChanged(event);
		}
	}

	public void fireTableStructureChanged() {
		fireTableChanged(new TableModelEvent(this, TableModelEvent.HEADER_ROW));
	}
}
