package supervisionApp;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javafx.event.ActionEvent;

public class ConnectBDDFrame extends JFrame {

	private static final long serialVersionUID = -6723438718315913487L;

	private JLabel labelUser = null;
	private JTextField textFieldUser = null;
	private JLabel labelPassword = null;
	private JPasswordField passwordField = null;

	private JPanel mainPanel = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ConnectBDDFrame();
	}

	/**
	 * Create the application.
	 */
	public ConnectBDDFrame() {
		initialize();
	}

	// public void setVisible(boolean b) {
	// this.setVisible(b);
	// }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		;

		// ConnectionManager newConnection = new
		// ConnectionManager("jdbc:mysql://192.168.20.11:3306/projet","root","password");
		// Boolean test = newConnection.ConnectDB();

		mainPanel = new JPanel();
		labelUser = new JLabel("Utilisateur : ");
		labelPassword = new JLabel("Mot de passe : ");
		passwordField = new JPasswordField();

		textFieldUser = new JTextField();
		textFieldUser.setColumns(10);

		// GridBagLayout
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(labelUser, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		mainPanel.add(textFieldUser, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		mainPanel.add(labelPassword, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		mainPanel.add(passwordField, c);

		this.add(mainPanel);
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Connection to the DataBase");
		// this.setIconImage(new ImageIcon("icons\\database.png").getImage());
		this.setVisible(true);

		JButton buttonConnect = new JButton("Connect");
		buttonConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// boolean connectUser = newConnection.ConnectUser(textFieldUser.getText(),
				// passwordField.getPassword());
				// if(connectUser) {
				// frame.setVisible(false);
				// new Frame();
				// } else {
				JOptionPane.showMessageDialog(ConnectBDDFrame.this, "Error : Connection impossible",
						"Connection failed", JOptionPane.ERROR_MESSAGE);
				// }
			}

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}
}
