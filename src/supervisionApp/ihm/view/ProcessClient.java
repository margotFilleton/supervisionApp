package supervisionApp.ihm.view;

import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

import supervisionApp.Computer;
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

	private JMenuBar menuBar = null;
	private JMenu menuFile = null;
	private JMenuItem menuReturn = null;

	private SupervisionController supervisionController = null;

	public ProcessClient(SupervisionController supervisionController, String fileName) {
		this.supervisionController = supervisionController;
		finalFinalName = fileName + ".txt";
		initComponent();
	}

	private void initComponent() {

		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuReturn = new JMenuItem("Return to show client process");
		
		menuFile.add(menuReturn);
		menuBar.add(menuFile);

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

		ClientPCInformationPanel clientPCInformationPanel = new ClientPCInformationPanel(tableModel);

		tabPane.addMyTab("Process Memory", tableClient);
		tabPane.setIconAt(0, (new ImageIcon("icons\\chip.png")));

		tabPane.addMyTab("CPU", cpuChartPanel);
		tabPane.setIconAt(1, (new ImageIcon("icons\\line-chart.png")));

		tabPane.addMyTab("Informations System", clientPCInformationPanel);
		tabPane.setIconAt(2, (new ImageIcon("icons\\computer.png")));
		// tabPane.addMyTab("JVM", panelJVMProcess);
		// tabPane.setIconAt(4, (new ImageIcon("icons\\java.png")));
		
		menuReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ShowClientPost(supervisionController);
				ProcessClient.this.dispose();
			}
		});

		new Computer(supervisionController);

		this.setJMenuBar(menuBar);
		this.add(tabPane);
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(finalFinalName);
		this.setVisible(true);
	}
}
