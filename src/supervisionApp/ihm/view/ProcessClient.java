package supervisionApp.ihm.view;

import javax.swing.JFrame;

import supervisionApp.ihm.model.ClientTableModel;

public class ProcessClient extends JFrame {

	private static final long serialVersionUID = 2528294466135748509L;

	private String finalFinalName = null;

	public ProcessClient(String fileName) {
		finalFinalName = fileName + ".txt";
		initComponent();
	}

	private void initComponent() {
		ClientTable tableClient = new ClientTable();
		ClientTableModel tableModel = new ClientTableModel(finalFinalName);
		tableClient.setModel(tableModel);
		tableModel.startMonitoring();

		this.add(tableClient);
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(finalFinalName);
		this.setVisible(true);
	}
}
