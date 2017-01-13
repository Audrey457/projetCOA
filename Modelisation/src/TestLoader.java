
import controlleur.PluginsLoader;
import controlleur.plugins.PluginTraitement;
import controlleur.plugins.PluginTransformation;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lalleaul
 */
public class TestLoader {

    /*public static void main(String[] args) {

        PluginsLoader pl = new PluginsLoader();

        ArrayList<String> files = new ArrayList<>();
        files.add("/home/infoetu/lalleaul/NetBeansProjects/plugins/dist/plugins.jar");
        pl.setFiles(files);
        try {
            ArrayList<PluginTraitement> plugins = pl.loadAllTreatmentPlugins();
            System.out.println("plugins chargés");
            for (PluginTraitement plugin : plugins) {
                System.out.println("Plugin : " + plugin.getLibelle());
            }
        } catch (Exception ex) {
            Logger.getLogger(TestLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/
}
