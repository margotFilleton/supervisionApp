package supervisionApp.ihm.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import supervisionApp.ihm.model.ClientTableModel;

public class ProcessClient extends JFrame {

	private static final long serialVersionUID = 2528294466135748509L;

	private String finalFinalName = null;
	private JTabbePanelData tabPane = null; 

	public ProcessClient(String fileName) {
		finalFinalName = fileName + ".txt";
		initComponent();
	}

	private void initComponent() {
		tabPane = new JTabbePanelData();
		ClientTable tableClient = new ClientTable();
		ClientTableModel tableModel = new ClientTableModel(finalFinalName);
		tableClient.setModel(tableModel);
		tableModel.startMonitoring();
		
		tabPane.addMyTab("Process Memory", tableClient);
		tabPane.setIconAt(0, (new ImageIcon("icons\\chip.png")));

//		tabPane.addMyTab("Process Memory Graph", createDemoPanel);
//		tabPane.setIconAt(1, (new ImageIcon("icons\\bar-chart.png")));
//		tabPane.addMyTab("CPU", cpuChartPanel);
//		tabPane.setIconAt(2, (new ImageIcon("icons\\line-chart.png")));
//		tabPane.addMyTab("Informations System", informationPC);
//		tabPane.setIconAt(3, (new ImageIcon("icons\\computer.png")));
//		tabPane.addMyTab("JVM", panelJVMProcess);
//		tabPane.setIconAt(4, (new ImageIcon("icons\\java.png")));

		this.add(tabPane);
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(finalFinalName);
		this.setVisible(true);
	}
}
