package supervisionApp.ihm.view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class JVMProcessChart {

	private boolean started = false;
	private JFreeChart chart = null;
	private ChartPanel chartPanel = null;

	private PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		long maxMemory = Runtime.getRuntime().maxMemory();
		long freeMemory = Runtime.getRuntime().freeMemory();
		long usedMemory= maxMemory - freeMemory;
		dataset.setValue("Available memory (bytes) ", maxMemory);
		dataset.setValue("Used memory (bytes) ", usedMemory);
		//dataset.setValue("Free memory (bytes)", freeMemory);
		
		
		return dataset;
	}

	private JFreeChart createChart(PieDataset dataset) {
		chart = ChartFactory.createPieChart("Memory of the JVM (bytes)", // chart title
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
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						PieDataset createDataset = createDataset();
						JPanel recreateChartPanel = recreateChartPanel(createDataset);

						if (tabPane != null && createDemoPanel != null) {
							tabPane.setComponentAt(4, recreateChartPanel);
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
