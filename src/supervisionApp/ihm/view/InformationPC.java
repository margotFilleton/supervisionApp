package supervisionApp.ihm.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.lang.management.ManagementFactory;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.management.OperatingSystemMXBean;

public class InformationPC extends JPanel {

	private static final long serialVersionUID = 2335438835766719594L;

	private JLabel message = new JLabel();
	private JLabel systemName = new JLabel();
	private JLabel systemVersion = new JLabel();

	public InformationPC() {
		OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		if (bean == null) {
			message.setText("Impossible de récuperer les informations du système.");
		} else {
			message.setText("Nombre de processeur(s) : " + +bean.getAvailableProcessors());
			systemName.setText("Operating System name : " + bean.getName());
			systemVersion.setText("Operating system version : " + bean.getVersion());
		}

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(message, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		add(systemName, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		add(systemVersion, c);
	}

	public static void main(String[] args) {
		InformationPC information = new InformationPC();
		JFrame frame = new JFrame();

		frame.add(information);
		frame.setSize(100, 100);
		frame.setVisible(true);
	}

}
