package supervisionApp.ihm.view;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.table.TableModel;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

import supervisionApp.ihm.controller.SupervisionController;
import supervisionApp.ihm.model.CPUChartModel;
import supervisionApp.ihm.model.ClientTableModel;
import supervisionApp.ihm.model.IChartModelListener;

public class ProcessClient extends JFrame {

	private static final long serialVersionUID = 2528294466135748509L;

	private String finalFinalName = null;
	private JTabbePanelData tabPane = null;

	// TABLE
	private ClientTable tableClient = null;
	private ClientTableModel tableModel = null;

	// CHART
	private CPUChartPanel cpuChartPanel = null;
	private CPUChartModel cpuChartModel = null;

	private SupervisionController supervisionController = null;

	public ProcessClient(SupervisionController supervisionController, String fileName) {
		this.supervisionController = supervisionController;
		finalFinalName = fileName + ".txt";
		initComponent();
	}

	private void initComponent() {

		tabPane = new JTabbePanelData();
		tableClient = new ClientTable(supervisionController);
		tableModel = new ClientTableModel(finalFinalName);

		tableClient.setModel(tableModel);
		tableModel.startMonitoring();

		cpuChartPanel = new CPUChartPanel();
		CPUChartPanel cpuChartPanel = new CPUChartPanel();
		cpuChartModel = new CPUChartModel(new IChartModelListener() {
			@Override
			public void dataChanged(XYDataset dataset) {
				JFreeChart chart = cpuChartPanel.getChart();
				XYPlot plot = chart.getXYPlot();
				plot.setDataset(dataset);
			}
		});

		cpuChartModel.startMonitoring(true, tableModel);

		List<String> infoPC = tableModel.getInfoPC();
		System.out.println("infoPC = " + infoPC);
		ClientPCInformationPanel clientPCInformationPanel = new ClientPCInformationPanel(infoPC);
		clientPCInformationPanel.getAllInfo();

		tabPane.addMyTab("Process Memory", tableClient);
		tabPane.setIconAt(0, (new ImageIcon("icons\\chip.png")));

		tabPane.addMyTab("CPU", cpuChartPanel);
		tabPane.setIconAt(1, (new ImageIcon("icons\\line-chart.png")));

		// tabPane.addMyTab("CPU", cpuChartPanel);
		// tabPane.setIconAt(1, (new ImageIcon("icons\\line-chart.png")));

		// tabPane.addMyTab("Informations System", clientPCInformationPanel);
		// tabPane.setIconAt(3, (new ImageIcon("icons\\computer.png")));
		// tabPane.addMyTab("JVM", panelJVMProcess);
		// tabPane.setIconAt(4, (new ImageIcon("icons\\java.png")));

		this.add(tabPane);
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(finalFinalName);
		this.setVisible(true);
	}
}
