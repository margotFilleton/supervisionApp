package supervisionApp.ihm.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.TimeSeriesCollection;

public class CPUChartPanel extends ChartPanel {
	
	private static final long serialVersionUID = -2425249312016160693L;


	public CPUChartPanel() {
		super(ChartFactory.createTimeSeriesChart(             
		         "Memory usage", 
		         "Second",              
		         "%",              
		         new TimeSeriesCollection(),             
		         false,              
		         false,              
		         false));
		initUI();
	}

	private void initUI() {
		this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		this.setBackground(Color.white);
		JFreeChart chart = getChart();
		XYPlot plot = chart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.BLUE);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		
		plot.getRangeAxis().setRange(0, 100);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.setTitle(new TextTitle("Utilisation de la CPU", new Font("Serif", java.awt.Font.BOLD, 18)));
	}
}
