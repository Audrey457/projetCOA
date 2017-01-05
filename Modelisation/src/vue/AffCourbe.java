package vue;

//import model.Tableau;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import model.Ligne;
import model.SerieToUse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class AffCourbe extends AbstractAction{
    
    private SerieToUse serie;
    
    public AffCourbe(){
        super("Affichage de la courbe");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int i = 0;
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(Ligne l: serie.getEnsLignes()){
            dataset.addValue((Number)serie.getValueAt(i, 1), (String)serie.getValueAt(i, 0), enabled);
            i++;
        }
        final JFreeChart curve = ChartFactory.createLineChart("Courbe", "babi", "boubou", dataset, PlotOrientation.HORIZONTAL, enabled, enabled, enabled);
        
    }
    
}
