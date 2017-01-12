package controlleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Ligne;
import vue.Fenetre;

public class ImportSerieURL implements ImportSerie {
	private ArrayList<Ligne> ensLignes = new ArrayList<>();
	String[] ligne;
	Fenetre vue;

	public ImportSerieURL(Fenetre vue) {
		this.vue = vue;
	}

	public ArrayList<Ligne> importerSerie(String adresse) {
		BufferedReader reader = null;
		String chaine = "";

		try {
			System.setProperty("http.proxyHost", "cache.univ-lille1.fr");
			System.setProperty("http.proxyPort", "3128");
			URL url = new URL(adresse);
			URLConnection urlConnection = url.openConnection();
			reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		} catch (IOException e) {
			System.out.println("URL incorrecte");
			return null;
		}

		try {
			chaine = reader.readLine();
		} catch (IOException e1) {
			System.out.println("Erreur de lecture de la ressource");
			return null;
		}

		// récupération des entetes du fichier
		String[] entetes = chaine.split(",");
		// utile pour récupérer l'indice
		ArrayList<String> listEntetes = new ArrayList<>();
		for (int i = 1; i < entetes.length; i++)
			listEntetes.add(entetes[i]);

		String s = (String) JOptionPane.showInputDialog(vue, "Quelle colonne voulez vous sélectionner?",
				"Selection colonne", JOptionPane.QUESTION_MESSAGE, null, listEntetes.toArray(),
				listEntetes.toArray()[0]);

		// lecture de la colonne selectionnée
		try {
			while ((chaine = reader.readLine()) != null) {
				ligne = chaine.split(",");
				ensLignes.add(new Ligne(ligne[0], Double.valueOf(ligne[listEntetes.indexOf(s) + 1])));
			}
		} catch (IOException e1) {
			System.out.println("Erreur de lecture de la ressource");
			return null;
		}

		// fermeture de la ressource
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println("Erreur fermeture de la ressource");
			return null;
		}

		return ensLignes;
	}

}
