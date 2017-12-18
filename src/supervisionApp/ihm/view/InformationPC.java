package supervisionApp.ihm.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.management.OperatingSystemMXBean;

public class InformationPC extends JPanel {

	private static final long serialVersionUID = 2335438835766719594L;

	private JLabel message = new JLabel();
	private JLabel systemName = new JLabel();
	private JLabel systemVersion = new JLabel();

	private JLabel hostName = new JLabel();
	private JLabel ipAdress = new JLabel();
	private JLabel processorId = new JLabel();
	private JLabel processorArchitect = new JLabel();
	private JLabel macAdress = new JLabel();

	public InformationPC() {
		OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		if (bean == null) {
			message.setText("Impossible de récuperer les informations du système.");
		} else {
			message.setText("Nombre de processeur(s) : " + +bean.getAvailableProcessors());
			systemName.setText("Operating System name : " + bean.getName());
			systemVersion.setText("Operating system version : " + bean.getVersion());
		}

		InetAddress ip;
		try {

			ip = InetAddress.getLocalHost();

			hostName.setText("Current host name : " + ip.getHostName());
			ipAdress.setText("Current IP address : " + ip.getHostAddress());
			processorId.setText("Processor identifier : " + System.getenv("PROCESSOR_IDENTIFIER"));
			processorArchitect.setText("Processor architecture : " + System.getenv("PROCESSOR_ARCHITECTURE"));

			NetworkInterface network = NetworkInterface.getByInetAddress(ip);

			byte[] mac = network.getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
			macAdress.setText("Current MAC address : " + sb.toString());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(hostName, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		add(ipAdress, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		add(systemName, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		add(systemVersion, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 4;
		add(message, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 5;
		add(processorId, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 6;
		add(processorArchitect, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 7;
		add(macAdress, c);

	}

	public static void main(String[] args) {
		InformationPC information = new InformationPC();
		JFrame frame = new JFrame();

		frame.add(information);
		frame.setSize(100, 100);
		frame.setVisible(true);
	}

}
