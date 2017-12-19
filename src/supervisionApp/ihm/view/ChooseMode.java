package supervisionApp.ihm.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import supervisionApp.ihm.controller.SupervisionController;
import supervisionApp.ihm.model.FileServer;

public class ChooseMode extends JFrame {

	private static final long serialVersionUID = -475883977717136276L;

	private JButton okButton = null;
	private JCheckBox checkBoxLocalMode = null;
	private JCheckBox checkBoxConnected = null;
	private JPanel mainPanel = null;
	private JPanel centerPanel = null;

	public static final String MODE_LOCAL = "MODE_LOCAL";
	public static final String MODE_CONNECTED = "MODE_CONNECTED";

	public ChooseMode(SupervisionController supervisionController) {

		okButton = new JButton("Ok");
		checkBoxLocalMode = new JCheckBox("Process Local");
		checkBoxConnected = new JCheckBox("Process other PC connected");
		mainPanel = new JPanel();
		centerPanel = new JPanel();

		checkBoxLocalMode.setSelected(false);
		checkBoxConnected.setSelected(false);

		centerPanel.add(checkBoxLocalMode);
		centerPanel.add(checkBoxConnected);

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(okButton, BorderLayout.SOUTH);

		checkBoxLocalMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean selected = checkBoxLocalMode.isSelected();
				checkBoxConnected.setSelected(!selected);
			}
		});

		checkBoxConnected.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean selected = checkBoxConnected.isSelected();
				checkBoxLocalMode.setSelected(!selected);
			}
		});

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean selectedConnected = checkBoxConnected.isSelected();
				boolean selectedLocalMode = checkBoxLocalMode.isSelected();
				if (selectedConnected) {
					FileServer fileServer = new FileServer();
					Thread thread = new Thread() {
						public void run() {
							try {
								fileServer.startFileServer();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					};
					thread.start();
					new ShowClientPost(supervisionController);
					ChooseMode.this.setVisible(false);
				} else if (selectedLocalMode) {
					ChooseMode.this.dispose();
					new Frame(supervisionController);
				}
				if (!selectedConnected && !selectedLocalMode) {
					JOptionPane.showMessageDialog(ChooseMode.this, "Select mode necessary", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		this.add(mainPanel);
		this.setSize(300, 200);
		this.setTitle("Chose mode");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
