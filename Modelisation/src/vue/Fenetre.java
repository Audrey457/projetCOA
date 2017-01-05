/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controlleur.SerieControleur;
import model.Ligne;
import model.SerieToUse;

public class Fenetre extends JFrame {

	private SerieControleur controller;
	private JPanel affich;
	private SerieToUse serieChro;
	private JTable vueTab;
	private JButton donnees, plugins, undo, redo, envoiParam, choixAffich, quit, sauver;
	private JTextField param;
	private JComboBox choixOpe;
	private JLabel indicParam;
	private JFileChooser fc;

	public Fenetre() {
		super();
		setTitle("logiGest");
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.LINE_AXIS));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1500, 800));
		this.initialiser();
		this.positionner();
		this.ajouterListeners();
		this.chargerEssai();
		pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	private void initialiser() {
		affich = new JPanel();
		donnees = new JButton("Charger données");
		plugins = new JButton("Charger plugin");
		undo = new JButton(new ImageIcon(getClass().getResource("/images/undo.jpg")));
		redo = new JButton(new ImageIcon(getClass().getResource("/images/redo.png")));
		envoiParam = new JButton("Ok");
		choixAffich = new JButton("Graph / Tableau");
		quit = new JButton("quitter");
		sauver = new JButton("Sauvegarder état actuel");
		param = new JTextField("Saisie paramètre");
		String textCombo[] = { "Choisir opération :", "-transformation logarithme", "-transformation de Box-Cox",
				"-transformation logistique", "-lissage à l'aide d'une moyenne mobile simple",
				"-lissage à l'aide d'une moyenne mobile pondérée", "-estimation de la saisonnalité",
				"-estimation d'une tendance linéaire", "-application d'un opérateur de différenciation",
				"-lissage exponentiel simple", "-lissage exponentiel double", "-méthode de Holt-Winters",
				"-graphe des résidus", "-variance résiduelle", "-autocorrélation des résidus" };
		choixOpe = new JComboBox(textCombo);
		indicParam = new JLabel("Indiquez paramètre numérique :");
		serieChro = new SerieToUse();
		vueTab = new JTable(serieChro);
		fc = new JFileChooser();
		controller = new SerieControleur(serieChro);
	}

	// positionnement de tous les elements dans la fenetre principale
	private void positionner() {
		Dimension eachPanDim = new Dimension(400, 200);
		JPanel gauche = new JPanel();

		/*
		 * Panneau de gauche
		 */
		gauche.setLayout(new BoxLayout(gauche, BoxLayout.PAGE_AXIS));
		gauche.setPreferredSize(new Dimension(400, 800));
		gauche.setMaximumSize(new Dimension(400, 800));
		gauche.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		// boutons charger donnees et plugins
		JPanel hautGauche = new JPanel();
		hautGauche.setLayout(new BoxLayout(hautGauche, BoxLayout.PAGE_AXIS));
		hautGauche.setPreferredSize(eachPanDim);
		donnees.setAlignmentX(Component.LEFT_ALIGNMENT);
		plugins.setAlignmentX(Component.LEFT_ALIGNMENT);
		hautGauche.add(donnees);
		hautGauche.add(Box.createRigidArea(new Dimension(0, 30)));
		hautGauche.add(plugins);

		// boutons undo redo
		JPanel milieuGauche = new JPanel();
		milieuGauche.setLayout(new BoxLayout(milieuGauche, BoxLayout.X_AXIS));
		milieuGauche.setPreferredSize(eachPanDim);
		milieuGauche.add(undo);
		milieuGauche.add(redo);

		// comboBox choix ope et saisie param
		JPanel basGauche = new JPanel();
		basGauche.setLayout(new BoxLayout(basGauche, BoxLayout.Y_AXIS));
		basGauche.setPreferredSize(new Dimension(400, 400));

		// comboBox choix opération
		choixOpe.setAlignmentX(Component.LEFT_ALIGNMENT);
		basGauche.add(Box.createRigidArea(new Dimension(0, 100)));
		basGauche.add(choixOpe);
		basGauche.add(Box.createRigidArea(new Dimension(0, 20)));
		indicParam.setAlignmentX(Component.LEFT_ALIGNMENT);
		basGauche.add(indicParam);
		// saisie parametre
		JPanel basGaucheParam = new JPanel();
		basGaucheParam.setLayout(new BoxLayout(basGaucheParam, BoxLayout.LINE_AXIS));
		basGaucheParam.setAlignmentX(Component.LEFT_ALIGNMENT);
		basGaucheParam.add(param);
		basGaucheParam.add(envoiParam);
		basGaucheParam.add(Box.createRigidArea(new Dimension(150, 0)));
		basGauche.add(Box.createRigidArea(new Dimension(0, 20)));
		basGauche.add(basGaucheParam);
		basGauche.add(Box.createRigidArea(new Dimension(0, 150)));

		// ajout de tous les panels dans gauche
		hautGauche.setAlignmentX(Component.LEFT_ALIGNMENT);
		milieuGauche.setAlignmentX(Component.LEFT_ALIGNMENT);
		basGauche.setAlignmentX(Component.LEFT_ALIGNMENT);

		gauche.add(hautGauche);
		gauche.add(milieuGauche);
		gauche.add(basGauche);

		/*
		 * panneau droit
		 */
		// affichage au centre
		affich.setPreferredSize(new Dimension(1100, 600));
		affich.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		affich.add(vueTab);

		// panneau du bas
		JPanel bas = new JPanel();
		bas.setLayout(new BoxLayout(bas, BoxLayout.PAGE_AXIS));
		bas.setPreferredSize(new Dimension(1100, 200));
		choixAffich.setAlignmentY(Component.TOP_ALIGNMENT);
		JPanel ctnSaveQuit = new JPanel();
		ctnSaveQuit.setLayout(new BoxLayout(ctnSaveQuit, BoxLayout.LINE_AXIS));

		ctnSaveQuit.add(Box.createRigidArea(new Dimension(500, 0)));
		ctnSaveQuit.add(sauver);
		ctnSaveQuit.add(Box.createRigidArea(new Dimension(300, 0)));
		quit.setAlignmentX(Component.RIGHT_ALIGNMENT);
		ctnSaveQuit.add(quit);
		bas.add(Box.createRigidArea(new Dimension(0, 50)));
		bas.add(choixAffich);
		bas.add(Box.createRigidArea(new Dimension(0, 50)));
		bas.add(ctnSaveQuit);

		JPanel droit = new JPanel();
		droit.setLayout(new BoxLayout(droit, BoxLayout.PAGE_AXIS));
		droit.setPreferredSize(new Dimension(1100, 800));
		droit.add(affich);
		droit.add(bas);

		// ajout de tous les panneaux dans la fenetre
		this.add(gauche);
		this.add(droit);
	}

	// ajout des listeners
	private void ajouterListeners() {
		donnees.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				chargerDonneesActionPerformed(evt);
			}
		});

		plugins.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// a faire
			}
		});

		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// a faire
			}
		});

		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// a faire
			}
		});

		choixOpe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// a faire
			}
		});

		envoiParam.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// a faire
			}
		});

		choixAffich.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// a faire
			}
		});

		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				quitActionPerformed(evt);
			}
		});

		sauver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				sauverActionPerformed(evt);
			}
		});

	}

	private void chargerDonneesActionPerformed(ActionEvent e) {
		int retourneVal = fc.showOpenDialog(this);

		if (retourneVal == JFileChooser.APPROVE_OPTION) {
			FileInputStream fis;
			ObjectInputStream ois;
			// try {
			// fis = new FileInputStream(fc.getSelectedFile().getPath());
			// ois = new ObjectInputStream(fis);
			// this.serieChro = (SerieChro2) ois.readObject();
			// fis.close();
			// ois.close();
			controller.fixeSerie(fc.getSelectedFile().getPath());

			JScrollPane sp = new JScrollPane(vueTab);
			affich.add(sp);
			// } catch (FileNotFoundException fnfe) {
			// System.out.println("Erreur lors du chargement de la série " +
			// fnfe.getMessage());
			// } catch (IOException ioe) {
			// System.out.println("Erreur lors du chargement de la série " +
			// ioe.getMessage());
			// } catch (ClassNotFoundException cnfe) {
			// System.out.println("Erreur lors du chargement de la série " +
			// cnfe.getMessage());
			// }
			// } else {

			// JOptionPane.showMessageDialog(this, "Vous n'avez rien
			// sélectionné.");

		}
	}

	private void sauverActionPerformed(ActionEvent e) {
		int userSelection = fc.showSaveDialog(this);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			FileOutputStream fich;
			ObjectOutputStream oos;
			String nomFich;
			nomFich = fc.getSelectedFile().toString().replace(".sc", "");
			nomFich += ".sc";
			try {
				fich = new FileOutputStream(nomFich);
				oos = new ObjectOutputStream(fich);
				oos.writeObject(this.serieChro);
				fich.close();
				oos.close();
			} catch (FileNotFoundException fnfe) {
				System.out.println(fnfe.getMessage());
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(this, "Vous n'avez pas sauvegardé.");
		}
	}

	private void chargerEssai() {
		ArrayList<Ligne> lignes = new ArrayList<>();
		Ligne lig1 = new Ligne("janv", 0.2569);
		Ligne lig2 = new Ligne("fev", 1.1458);
		Ligne lig3 = new Ligne("mar", 2.369);

		lignes.add(lig1);
		lignes.add(lig2);
		lignes.add(lig3);

		serieChro.setEnsLignes(lignes);
		JScrollPane sp = new JScrollPane(vueTab);
		affich.add(sp);
	}

	private void quitActionPerformed(ActionEvent evt) {
		this.dispose();
	}
}
