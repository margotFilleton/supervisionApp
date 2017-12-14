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

	private static JFreeChart createChart(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart("Size process memory (Mo)", // chart title
				dataset, 
				true, 
				true, false);
		
		return chart;
	}

	public JPanel createChartPanel() {
		JFreeChart chart = createChart(createDataset());
		return new ChartPanel(chart);
	}
}
