package controlleur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Ligne;
import model.SerieToUse;
import vue.Fenetre;

public class ImportSerieCSV implements ImportSerie {
	private ArrayList<Ligne> ensLignes = new ArrayList<>();
	private SerieToUse serie;
	String[] ligne;
	Fenetre vue;

	public ImportSerieCSV(Fenetre vue) {
		this.vue = vue;
	}

	public SerieToUse importerSerie(String chemin) {
		try {
			serie = new SerieToUse();
			BufferedReader fichier = new BufferedReader(new FileReader(chemin));
			String chaine;
			fichier.readLine();
			while ((chaine = fichier.readLine()) != null) {
				ligne = chaine.split(",");
				ensLignes.add(new Ligne(ligne[0], Double.valueOf(ligne[1])));
			}
			fichier.close();

		} catch (IOException e) {
			System.out.println("Le fichier est introuvable !");
		}
		serie.setEnsLignes(ensLignes);
		return serie;
	}

}
