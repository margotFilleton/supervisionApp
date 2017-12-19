package supervisionApp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import supervisionApp.ihm.controller.SupervisionController;
import supervisionApp.ihm.view.ChooseMode;

public class UserFrame {

	private JFrame frame;
	private JTextField textFieldUser;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame window = new UserFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserFrame() {
		initialize();
	}
	
	

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 227, 148);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Connection to the DataBase");
		frame.setIconImage(new ImageIcon("icons\\database.png").getImage());
		
		ConnectionManager newConnection = new ConnectionManager("jdbc:mysql://192.168.20.56:3306/projet","root","password");
		boolean connectDB = newConnection.ConnectDB();
		JLabel lblUser = new JLabel("Utilisateur");
		
		textFieldUser = new JTextField();
		textFieldUser.setColumns(10);
		
		JLabel lblPassword = new JLabel("Mot de passe");
		
		JButton buttonConnect = new JButton("Connect");
		buttonConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean connectUser = newConnection.ConnectUser(textFieldUser.getText(), passwordField.getPassword());
				if(connectUser) {
					frame.setVisible(false);
					SupervisionController supervisionController = new SupervisionController();
					supervisionController.setUser(newConnection.getUser());
					new ChooseMode(supervisionController);
				} else {
					 JOptionPane.showMessageDialog(frame, "Error : Connection impossible", 
					      "Connection failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		passwordField = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(25, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(buttonConnect)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPassword)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblUser)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(26))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUser))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonConnect)
					.addGap(23))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
