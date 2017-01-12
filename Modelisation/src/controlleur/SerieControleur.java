
package controlleur;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Ligne;
import model.SerieToUse;
import vue.Fenetre;

public class SerieControleur {
	private SerieToUse serie;
	private Fenetre vue;
	private ImportSerie importserie;

	public SerieControleur(SerieToUse model) {
		serie = model;
	}

	public void fixeSerie(String chemin) {
		if (!chemin.substring(chemin.lastIndexOf('.') + 1).equals("csv")) {
			JOptionPane.showMessageDialog(vue, "Fichier incorrect.", "Erreur format", JOptionPane.ERROR_MESSAGE);
		} else if (chemin.substring(0, 4).equals("http")) {
			importserie = new ImportSerieURL(vue);
			ArrayList<Ligne> lignes = importserie.importerSerie(chemin);
			if (lignes != null)
				serie.setEnsLignes(lignes);
		} else {
			importserie = new ImportSerieCSV(vue);
			ArrayList<Ligne> lignes = importserie.importerSerie(chemin);
			if (lignes != null)
				serie.setEnsLignes(lignes);
		}
	}

	// utile pour faire un éventuel control de données et l'afficher seulement
	// sur la vue
	public void addView(Fenetre vue) {
		this.vue = vue;
	}
}
