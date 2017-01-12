/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controlleur.PluginsLoader;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;

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
import controlleur.plugins.PluginBase;
import controlleur.plugins.PluginTraitement;
import controlleur.plugins.PluginTransformation;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SerieToUse;

public class Fenetre extends JFrame implements Observer {

    private SerieControleur controleur;
    private JPanel affich, cardAffichCourbe, cardAffichTab;
    private SerieToUse serie;
    private JTable vueTab;
    private JButton donneesCSV, plugins, undo, redo, envoiParam, choixAffichTab, choixAffichCourbe, quit, sauver,
            selectUrl;
    private JTextField param;
    private JTextField urlRessource;
    private JComboBox choixOpe;
    private JLabel indicParam;
    private JLabel indicUrl;
    private JFileChooser fc;
    private ArrayList<PluginTransformation> plugsTransfo;
    private ArrayList<PluginTraitement> plugsTrait;
    private String textCombo[] = {"Aucun plugin", ""};

    public Fenetre(SerieToUse serie, SerieControleur controleur) {
        super();
        this.serie = serie;
        this.controleur = controleur;
        serie.addObserver(this);
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
        affich = new JPanel(new CardLayout());
        cardAffichTab = new JPanel();
        cardAffichCourbe = new JPanel();
        donneesCSV = new JButton("Charger données depuis un fichier CSV");
        plugins = new JButton("Charger plugin");
        undo = new JButton(new ImageIcon(getClass().getResource("/images/undo.jpg")));
        redo = new JButton(new ImageIcon(getClass().getResource("/images/redo.png")));
        envoiParam = new JButton("Ok");
        selectUrl = new JButton("Ok");
        choixAffichTab = new JButton("Tableau");
        choixAffichCourbe = new JButton("Courbe");
        quit = new JButton("quitter");
        sauver = new JButton("Sauvegarder état actuel");
        param = new JTextField("Saisie paramètre");
        urlRessource = new JTextField("ex : http://google.fr/fichier.csv");
        indicParam = new JLabel("Indiquez paramètre numérique :");
        indicUrl = new JLabel("Indiquez l'url de la ressource en ligne :");
        vueTab = new JTable(serie);
        fc = new JFileChooser();
        plugsTransfo = new ArrayList<>();
        plugsTrait = new ArrayList<>();

        boolean tranfoEmpty, traitEmpty;
        tranfoEmpty = plugsTransfo.isEmpty();
        traitEmpty = plugsTrait.isEmpty();

        if (tranfoEmpty && traitEmpty) {
            choixOpe = new JComboBox(textCombo);
        } else {
            int i = 1;
            textCombo = new String[plugsTransfo.size() + plugsTrait.size() + 1];
            textCombo[0] = "Choisir opération : ";
            if (!tranfoEmpty) {
                for (PluginTransformation p : plugsTransfo) {
                    textCombo[i] = p.getLibelle();
                    i++;
                }
            }
            if (!traitEmpty) {
                for (PluginTraitement p : plugsTrait) {
                    textCombo[i] = p.getLibelle();
                    i++;
                }
            }
            choixOpe = new JComboBox(textCombo);
        }

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

        // boutons charger donnees depuis CSV, charger donnees depuis URL et plugins
        JPanel hautGauche = new JPanel();
        hautGauche.setLayout(new BoxLayout(hautGauche, BoxLayout.PAGE_AXIS));
        hautGauche.setPreferredSize(eachPanDim);
        donneesCSV.setAlignmentX(Component.LEFT_ALIGNMENT);
        plugins.setAlignmentX(Component.LEFT_ALIGNMENT);
        hautGauche.add(plugins);
        hautGauche.add(Box.createRigidArea(new Dimension(0, 30)));
        hautGauche.add(donneesCSV);
        hautGauche.add(Box.createRigidArea(new Dimension(0, 30)));
        hautGauche.add(indicUrl);
        hautGauche.add(Box.createRigidArea(new Dimension(0, 10)));
        JPanel hautGaucheUrl = new JPanel();
        hautGaucheUrl.setLayout(new BoxLayout(hautGaucheUrl, BoxLayout.LINE_AXIS));
        hautGaucheUrl.setAlignmentX(Component.LEFT_ALIGNMENT);
        hautGaucheUrl.add(urlRessource);
        hautGaucheUrl.add(this.selectUrl);
        hautGauche.add(hautGaucheUrl);
        hautGauche.add(Box.createRigidArea(new Dimension(0, 30)));

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
        JScrollPane sp = new JScrollPane(vueTab);
        cardAffichTab.add(sp);
        affich.add(cardAffichTab, "Tableau");
        affich.add(cardAffichCourbe, "Courbe");

        // panneau du bas
        JPanel bas = new JPanel();
        bas.setLayout(new BoxLayout(bas, BoxLayout.PAGE_AXIS));
        bas.setPreferredSize(new Dimension(1100, 200));
        choixAffichTab.setAlignmentY(Component.TOP_ALIGNMENT);
        choixAffichCourbe.setAlignmentY(Component.TOP_ALIGNMENT);
        choixAffichTab.setAlignmentX(Component.CENTER_ALIGNMENT);
        choixAffichCourbe.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel ctnSaveQuit = new JPanel();
        JPanel ctnChoix = new JPanel();
        ctnSaveQuit.setLayout(new BoxLayout(ctnSaveQuit, BoxLayout.LINE_AXIS));
        ctnSaveQuit.add(Box.createRigidArea(new Dimension(400, 0)));
        ctnSaveQuit.add(sauver);
        ctnSaveQuit.add(Box.createRigidArea(new Dimension(300, 0)));
        ctnChoix.setLayout(new BoxLayout(ctnChoix, BoxLayout.LINE_AXIS));
        quit.setAlignmentX(Component.RIGHT_ALIGNMENT);
        ctnSaveQuit.add(quit);
        ctnChoix.add(Box.createRigidArea(new Dimension(0, 100)));
        ctnChoix.add(choixAffichTab);
        ctnChoix.add(Box.createRigidArea(new Dimension(10, 0)));
        ctnChoix.add(choixAffichCourbe);
        bas.add(ctnChoix);
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

    //permet d'ajouter toutes les operations qui sont de base dans l'application
    private void ajouterPluginsDeBase() {
        //A faire
    }

    // ajout des listeners
    private void ajouterListeners() {
        donneesCSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                chargerDonneesActionPerformed(evt);
            }
        });

        plugins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    chargerPluginActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
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

        selectUrl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                controleur.fixeSerie(urlRessource.getText());
            }
        });

        choixAffichTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                choixAffichTabActionPerformed(evt);
            }
        });

        choixAffichCourbe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                choixAffichCourbeActionPerformed(evt);
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

    private void chargerPluginActionPerformed(ActionEvent e) throws Exception {
        int retourneVal = fc.showOpenDialog(this);
        if (retourneVal == JFileChooser.APPROVE_OPTION) {
            PluginsLoader pl = new PluginsLoader(fc.getSelectedFile().getPath());
            plugsTransfo.addAll(pl.loadAllTransformPlugins());
            plugsTrait.addAll(pl.loadAllTreatmentPlugins());
        }

        int i = 1;
        textCombo = new String[plugsTransfo.size() + plugsTrait.size() + 1];
        textCombo[0] = "Choisir opération : ";
        boolean tranfoEmpty, traitEmpty;
        tranfoEmpty = plugsTransfo.isEmpty();
        traitEmpty = plugsTrait.isEmpty();
        if (!tranfoEmpty) {
            for (PluginTransformation p : plugsTransfo) {
                textCombo[i] = p.getLibelle();
                i++;
            }
        }
        if (!traitEmpty) {
            for (PluginTraitement p : plugsTrait) {
                textCombo[i] = p.getLibelle();
                i++;
            }
        }
        choixOpe = new JComboBox(textCombo);
    }

    private void choixAffichTabActionPerformed(ActionEvent e) {
        CardLayout cl = (CardLayout) (affich.getLayout());
        cl.show(affich, "Tableau");

    }

    private void choixAffichCourbeActionPerformed(ActionEvent e) {
        CardLayout cl = (CardLayout) (affich.getLayout());
        cl.show(affich, "Courbe");

    }

    private void chargerDonneesActionPerformed(ActionEvent e) {
        int retourneVal = fc.showOpenDialog(this);
        if (retourneVal == JFileChooser.APPROVE_OPTION) {
            controleur.fixeSerie(fc.getSelectedFile().getPath());
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
                oos.writeObject(this.serie);
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

    }

    private void quitActionPerformed(ActionEvent evt) {
        this.dispose();
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        if (arg1.equals("serieChange")) {
            serie.fireTableStructureChanged();
        }

    }
}
