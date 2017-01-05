
import controlleur.SerieControleur;
import model.SerieToUse;
import vue.Fenetre;

public class LogiGest {

	public LogiGest() {
		SerieToUse serie = new SerieToUse();
		SerieControleur serieControl = new SerieControleur(serie);
		Fenetre fenetre = new Fenetre(serie, serieControl);
		serieControl.addView(fenetre);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new LogiGest();
			}
		});
	}

}
