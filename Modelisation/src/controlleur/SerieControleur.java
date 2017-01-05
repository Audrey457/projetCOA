
package controlleur;

import javax.swing.JOptionPane;

import model.SerieToUse;
import vue.Fenetre;

public class SerieControleur {
	private SerieToUse serie;
	private Fenetre vue;
	private ImportSerie importserie = new ImportSerieCSV();

	public SerieControleur(SerieToUse model) {
		serie = model;
	}

	public void fixeSerie(String chemin) {
		if (!chemin.substring(chemin.indexOf('.') + 1).equals("csv")) {
			JOptionPane.showMessageDialog(vue, "Fichier incorrect.", "Erreur format", JOptionPane.ERROR_MESSAGE);
		} else {
			serie.setEnsLignes(importserie.importerSerie(chemin));
		}
	}

	// utile pour faire un éventuel control de données et l'afficher seulement
	// sur la vue
	public void addView(Fenetre vue) {
		this.vue = vue;
	}
}
