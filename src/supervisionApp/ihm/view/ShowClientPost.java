package supervisionApp.ihm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowClientPost extends JFrame {

	private static final long serialVersionUID = 6017672191027006837L;
	private ArrayList<String> fileNameList = null;
	private JPanel mainPanel = null;

	public ShowClientPost() {

		mainPanel = new JPanel();

		if (fileNameList == null) {
			fileNameList = new ArrayList<String>();
		}
		String dirName = "C:\\client_connected\\";
		File dir = new File(dirName);
		File[] f = dir.listFiles();
		for (int i = 0; i < f.length; i++) {
			if (f[i].isFile()) {
				fileNameList.add(String.valueOf(f[i]));
				String fullClientName = String.valueOf(f[i]);
				int index = fullClientName.lastIndexOf('\\');
				String clientNameTxt = fullClientName.substring(index + 1);
				String[] split = clientNameTxt.split(".txt");
				String clientName = split[0];
				JPanel createNewClientPanel = createNewClientPanel(clientName);
				mainPanel.add(createNewClientPanel);
			}
		}

		mainPanel.setBackground(Color.WHITE);
		this.add(mainPanel);
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Choose client ");
		this.setIconImage(new ImageIcon("icons\\monitor.png").getImage());
		this.setVisible(true);
	}

	private JPanel createNewClientPanel(String fileName) {
		boolean isMine = false;
		String hostName = null;
		InetAddress localHostIp = null;
		try {
			localHostIp = InetAddress.getLocalHost();
			String[] split = String.valueOf(localHostIp).split("/");
			hostName = split[0];
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		if (fileName.equals(hostName)) {
			isMine = true;
		}

		JButton button = new JButton();
		JPanel newClientPanel = new JPanel();
		newClientPanel.setBackground(Color.WHITE);
		if (isMine) {
			button.setText(fileName + " (yours)");
		} else {
			button.setText(fileName);
		}

		button.setBackground(Color.BLACK);
		button.setForeground(Color.WHITE);
		newClientPanel.setLayout(new BorderLayout());

		newClientPanel.add(new JLabel(new ImageIcon("icons\\monitor.png")), BorderLayout.CENTER);
		newClientPanel.add(button, BorderLayout.SOUTH);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ShowClientPost.this.dispose();
				new ProcessClient(fileName);
			}
		});

		return newClientPanel;
	}

	public static void main(String[] args) {
		new ShowClientPost();
	}
}
