
package controlleur;

import model.SerieToUse;
import vue.VueTableau;

public class SerieControleur {
	private SerieToUse serie;
	private VueTableau vue;
	private ImportSerie importserie = new ImportSerieCSV();

	public SerieControleur(SerieToUse model) {
		serie = model;
	}

	public void fixeSerie(String chemin) {
		serie.setEnsLignes(importserie.importerSerie(chemin));
	}

	// utile pour faire un éventuel control de données et l'afficher seulement
	// sur la vue
	public void addView(VueTableau vue) {
		this.vue = vue;
	}
}
