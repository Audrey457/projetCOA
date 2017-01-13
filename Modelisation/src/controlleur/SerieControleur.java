
package controlleur;

import javax.swing.JOptionPane;

import model.SerieToUse;
import vue.AffCourbe;
import vue.Fenetre;

public class SerieControleur {
	private SerieToUse serie;
	private Fenetre vue;
	private AffCourbe courbe;
	private ImportSerie importserie;

	public SerieControleur(SerieToUse model) {
		serie = model;
	}

	public void fixeSerie(String chemin) {
		if (!chemin.substring(chemin.lastIndexOf('.') + 1).equals("csv")) {
			JOptionPane.showMessageDialog(vue, "Fichier incorrect.", "Erreur format", JOptionPane.ERROR_MESSAGE);
		} else if (chemin.substring(0, 4).equals("http")) {
			importserie = new ImportSerieURL(vue);
			SerieToUse importSerie = importserie.importerSerie(chemin);
			if (importSerie != null)
				serie.setSerieToUse(importSerie);
		} else {
			importserie = new ImportSerieCSV(vue);
			SerieToUse importSerie = importserie.importerSerie(chemin);
			if (importSerie != null)
				serie.setSerieToUse(importSerie);
		}
	}

	// utile pour faire un éventuel control de données et l'afficher seulement
	// sur la vue
	public void addView(Fenetre vue) {
		this.vue = vue;
	}

	public void addCourbe(AffCourbe courbe) {
		this.courbe = courbe;
	}
}
