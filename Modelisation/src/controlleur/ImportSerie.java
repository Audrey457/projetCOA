package controlleur;

import java.util.ArrayList;

import model.Ligne;

public interface ImportSerie {
	// différentes méthodes d'import pourront implémenter cette méthode
	public ArrayList<Ligne> importerSerie(String chemin);
}
