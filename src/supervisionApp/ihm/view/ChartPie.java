package supervisionApp.ihm.view;

import java.awt.Component;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class ChartPie {

	private Map<String, String> mapNomTaille = null;
	private boolean started = false;
	private JFreeChart chart = null;
	private ChartPanel chartPanel = null;

	public ChartPie(Map<String, String> mapNomTaille) {
		this.mapNomTaille = mapNomTaille;
	}

	private PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		if (mapNomTaille.size() > 0 && (!mapNomTaille.isEmpty())) {
			for (Map.Entry<String, String> e : mapNomTaille.entrySet()) {
				dataset.setValue(String.valueOf(e.getKey()), Double.valueOf(e.getValue()));
			}
		}
		return dataset;
	}

	private JFreeChart createChart(PieDataset dataset) {
		chart = ChartFactory.createPieChart("Size process memory (Mo) 50 > ", // chart title
				dataset, true, true, false);

		return chart;
	}

	public void startMonitoring(JTabbedPane tabPane, JPanel createDemoPanel) {
		if (!started) {
			started = true;
			Thread thread = new Thread() {
				public void run() {
					while (started) {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						PieDataset createDataset = createDataset();
						JPanel recreateChartPanel = recreateChartPanel(createDataset);

						if (tabPane != null && createDemoPanel != null) {
							tabPane.setComponentAt(1, recreateChartPanel);
						}

						if (chart != null) {
							chart.fireChartChanged();
							chartPanel.validate();
							chartPanel.repaint();
						}
					}
				};
			};
			thread.start();
		}
	}

	public void stopMonitoring() {
		if (started) {
			started = false;
		}
	}

	public JPanel createChartPanel() {
		chart = createChart(createDataset());
		chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

	public JPanel recreateChartPanel(PieDataset createDataset) {
		chart = createChart(createDataset);
		chartPanel = new ChartPanel(chart);
		return chartPanel;
	}
}
