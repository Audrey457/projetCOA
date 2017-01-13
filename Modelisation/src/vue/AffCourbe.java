package vue;

import java.awt.Dimension;

import javax.swing.JPanel;

import model.Ligne;
import model.SerieToUse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class AffCourbe extends JPanel {

	private SerieToUse serie;
	private DefaultCategoryDataset data;
	private JFreeChart lineChart;

	public AffCourbe(SerieToUse serie) {
		data = new DefaultCategoryDataset();
		this.serie = serie;
		lineChart = ChartFactory.createLineChart("Courbe", "Date", "Données", data, PlotOrientation.VERTICAL, false,
				true, false);
		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setMouseZoomable(false);
		chartPanel.setPreferredSize(new Dimension(1000, 500));
		lineChart.setTitle("Serie Chronologique");
		this.add(chartPanel);
	}

	public void majCourbe() {
		data.clear();
		for (Ligne l : serie.getEnsLignes()) {
			data.addValue(l.getValeur(), serie.getColumnName(1), l.getValDate());
		}
		CategoryPlot plot = lineChart.getCategoryPlot();
		ValueAxis yAxis = plot.getRangeAxis();
		// changer le nom de l'axe des y
		yAxis.setLabel(serie.getColumnName(1));
	}

}
