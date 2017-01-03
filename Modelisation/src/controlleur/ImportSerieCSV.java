package controlleur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Ligne;

public class ImportSerieCSV implements ImportSerie {
	private ArrayList<Ligne> ensLignes = new ArrayList<>();
	String[] ligne;

	public ArrayList<Ligne> importerSerie(String chemin) {
		try {
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
		return ensLignes;
	}

}
