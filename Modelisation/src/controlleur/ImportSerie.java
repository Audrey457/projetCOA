package controlleur;

import model.SerieToUse;

public interface ImportSerie {
	// différentes méthodes d'import pourront implémenter cette méthode
	public SerieToUse importerSerie(String chemin);
}
