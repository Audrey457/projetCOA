package controlleur;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import controlleur.plugins.*;

/**
 * Classe gérant le chargement et la validation des plugins
 *
 * @author LLalleau Inspiré par la classe de Vincent Lainé (dev01,
 * http://vincentlaine.developpez.com/ ) Modification de l'alogrithme pour
 * retirer les methodes dépreciées et adapter le loader au besoin du projet
 *
 */
public class PluginsLoader {

    private final String ITRAITEMENT = "controlleur.plugins.PluginTraitement";
    private final String ITRANSFORMATION = "controlleur.plugins.PluginTransformation";

    private String file;

    private ArrayList classPluginsTraitement;
    private ArrayList classPluginsTransformation;

    /**
     * Constructeur par défaut
     */
    public PluginsLoader() {
        this.classPluginsTraitement = new ArrayList();
        this.classPluginsTransformation = new ArrayList();

    }

    /**
     * Constucteur initialisant le tableau de fichier à charger.
     *
     * @param pathFile String du jarà charger.
     */
    public PluginsLoader(String jarFile) {
        this();
        this.file = jarFile;
    }

    /**
     * Défini l'ensemble des fichiers à charger
     *
     * @param files
     */
    public void setFiles(String jarFile) {
        this.file = jarFile;
    }

    /**
     * Fonction de chargement de tout les plugins de type PluginTraitement
     *
     * @return Une collection de PluginTraitement contenant les instances des
     * plugins
     * @throws Exception si files = null ou files.size = 0
     */
    public ArrayList<PluginTraitement> loadAllTreatmentPlugins() throws Exception {

        this.initializeLoader();

        ArrayList<PluginTraitement> tmpPlugins = new ArrayList<PluginTraitement>();

        for (Object plugin : this.classPluginsTraitement) {
            /**
             * On crée une nouvelle instance de l'objet contenu dans la liste
             * grâce à newInstance() et on le cast en Plugin.
             */
            tmpPlugins.add((PluginTraitement) ((Class) plugin).newInstance());

        }

        return tmpPlugins;
    }

    public ArrayList<PluginTransformation> loadAllTransformPlugins() throws Exception {

        this.initializeLoader();

        ArrayList<PluginTransformation> tmpPlugins = new ArrayList<PluginTransformation>();

        for (Object plugin : this.classPluginsTransformation) {
            /**
             * On crée une nouvelle instance de l'objet contenu dans la liste
             * grâce à newInstance() et on le cast en Plugin.
             */
            tmpPlugins.add((PluginTransformation) ((Class) plugin).newInstance());

        }

        return tmpPlugins;
    }

    private void initializeLoader() throws Exception {
        // On vérifie que la liste des plugins à charger a été initialisée
        if (this.file == null) {
            throw new Exception("Pas de fichier spécifié");
        }

        // Pour éviter le double chargement des plugins
        if (this.classPluginsTransformation.size() != 0 || this.classPluginsTraitement.size() != 0) {
            System.out.println("Plugins déjà chargé");
            return;
        }

        File f = new File(this.file);
        // Pour charger le .jar en memoire
        URLClassLoader loader;
        // Pour la comparaison de chaines
        String tmp = "";
        // Pour le contenu de l'archive jar
        Enumeration<JarEntry> enumeration;
        // Pour déterminer quels sont les interfaces implémentées
        Class tmpClass = null;

        if (!f.exists()) {

            System.out.println(f.getAbsolutePath() + " n'existe pas");
            return;
        }

        URL u = f.toURI().toURL();
        // On crée un nouveau URLClassLoader pour charger le jar qui se
        // trouve en dehors du CLASSPATH
        loader = new URLClassLoader(new URL[]{u});

        // On charge le jar en mémoire
        JarFile jar = new JarFile(f.getAbsolutePath());

        // On récupère le contenu du jar
        enumeration = jar.entries();

        while (enumeration.hasMoreElements()) {

            JarEntry je = enumeration.nextElement();

            if (!je.isDirectory() && je.getName().endsWith(".class")) {

                tmp = je.getName();
                tmp = tmp.substring(0, tmp.length() - 6);
                tmp = tmp.replaceAll("/", ".");

                tmpClass = Class.forName(tmp, true, loader);

                for (int i = 0; i < tmpClass.getInterfaces().length; i++) {

                    if (tmpClass.getInterfaces()[i].getName().toString()
                            .equals(this.ITRAITEMENT)) {
                        this.classPluginsTraitement.add(tmpClass);
                    } else if (tmpClass.getInterfaces()[i].getName().toString()
                            .equals(this.ITRANSFORMATION)) {
                        this.classPluginsTransformation.add(tmpClass);
                    }
                }

            }
        }

    }
}
