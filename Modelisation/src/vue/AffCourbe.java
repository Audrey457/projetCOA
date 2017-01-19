package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JPanel;
import model.Ligne;
import model.SerieToUse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class AffCourbe extends JPanel {

	private SerieToUse serie;
	XYDataset dataset = new TimeSeriesCollection();
	XYPlot plot;
	JFreeChart chart;

	public AffCourbe(SerieToUse serie) {
		this.serie = serie;
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(1050, 500));
		this.add(chartPanel);
	}

	private JFreeChart createChart(final XYDataset dataset) {

		chart = ChartFactory.createTimeSeriesChart("Serie Chronologique", "Date", "Y", dataset, true,
				true, false);

		chart.setBackgroundPaint(Color.white);
		chart.removeLegend();
		plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		// plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, false);
		renderer.setSeriesShapesVisible(1, false);
		plot.setRenderer(renderer);

		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		return chart;
	}

	public void majCourbe() {
		ValueAxis yAxis = plot.getRangeAxis();
		// changer le nom de l'axe des y
		yAxis.setLabel(serie.getColumnName(1));

		setDefaultCourbe();
	}

	// Supprime toutes les courbes pour la remplacer par celle de serie
	public void setDefaultCourbe() {
		for (int i = 0; i < ((TimeSeriesCollection) dataset).getSeriesCount(); i++) {
			((TimeSeriesCollection) dataset).getSeries(i).clear();
		}
		
		addCourbe(this.serie);
	}
	
	public void uniqueCourbe(SerieToUse serie) {
		for (int i = 0; i < ((TimeSeriesCollection) dataset).getSeriesCount(); i++) {
			((TimeSeriesCollection) dataset).getSeries(i).clear();
		}
		
		addCourbe(serie);
	}

	public void addCourbe(SerieToUse serie) {
		TimeSeries s = new TimeSeries(serie.getColumnName(1));
		for (Ligne l : serie.getEnsLignes()) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = dateFormat.parse(l.getValDate());
			} catch (Exception e) {
				System.err.println("Format de date invalide. Usage : yyyy-MM-dd");
				System.err.println(e.getMessage());
			}
			s.add(new Day(date), l.getValeur());
		}
		((TimeSeriesCollection) dataset).addSeries(s);
		chart.fireChartChanged();
	}

}
