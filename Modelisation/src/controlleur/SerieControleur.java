
package controlleur;

import model.SerieChro2;
import vue.VueTableau;

public class SerieControleur {
	private SerieChro2 serie;
	private VueTableau vue;
	private ImportSerie importserie = new ImportSerieCSV();

	public SerieControleur(SerieChro2 model) {
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
