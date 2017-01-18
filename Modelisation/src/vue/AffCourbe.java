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
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;


public class AffCourbe extends JPanel {

	private SerieToUse serie;
	XYDataset dataset = new TimeSeriesCollection();
	XYPlot plot;

	public AffCourbe(SerieToUse serie) {
		this.serie = serie;
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(1000, 500));
		this.add(chartPanel);
	}

	private JFreeChart createChart(final XYDataset dataset) {

		final JFreeChart chart = ChartFactory.createTimeSeriesChart("Serie Chronologique", "Date", "Y", dataset, true,
				true, false);

		chart.setBackgroundPaint(Color.white);

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

	private void setDefaultCourbe() {

		TimeSeries s1 = new TimeSeries("Data");

		for (Ligne l : serie.getEnsLignes()) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = dateFormat.parse(l.getValDate());
			} catch (Exception e) {
				System.err.println("Format de date invalide. Usage : yyyy-MM-dd");
				System.err.println(e.getMessage());
			}
			s1.add(new Day(date), l.getValeur());
		}

		((TimeSeriesCollection) dataset).setDomainIsPointsInTime(true);

		((TimeSeriesCollection) dataset).addSeries(s1);
	}

	public void addCourbe() {
		TimeSeries s2 = new TimeSeries("Test");
		s2.add(new Month(2, 2001), 129.6);
		s2.add(new Month(3, 2001), 123.2);
		s2.add(new Month(4, 2001), 117.2);
		s2.add(new Month(5, 2001), 124.1);
		((TimeSeriesCollection) dataset).addSeries(s2);
	}

}
