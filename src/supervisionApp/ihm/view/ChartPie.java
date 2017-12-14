package supervisionApp.ihm.view;

import java.util.Map;

import javax.swing.JPanel;

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
				System.out.println("String.valueOf(e.getKey()) = " + String.valueOf(e.getKey()));
				dataset.setValue(String.valueOf(e.getKey()), Double.valueOf(e.getValue()));
			}
		}
		return dataset;
	}

	private JFreeChart createChart(PieDataset dataset) {
		chart = ChartFactory.createPieChart("Size process memory (Mo)", // chart title
				dataset, true, true, false);

		return chart;
	}

	public void startMonitoring() {
		if (!started) {
			started = true;
			Thread thread = new Thread() {
				public void run() {
					while (started) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						PieDataset createDataset = createDataset();
						
						if (chart != null) {
							chart.fireChartChanged();
							chart.setNotify(true);
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
}
