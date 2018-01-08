package supervisionApp.ihm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ProcessKilledFrame extends JFrame {

	private static final long serialVersionUID = -6000638411036469691L;

	private JPanel panel = null;
	// private JTextArea textArea = null;

	public ProcessKilledFrame(List<String> list) {

		panel = new JPanel();

		if (list == null) {
			JOptionPane.showMessageDialog(this, "Kill task is empty", "Error", JOptionPane.ERROR_MESSAGE);
		} else {

			for (String str : list) {
				JButton button = new JButton("Restart " + str);
				panel.add(button);

				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						try {
							String[] split = str.split(".exe");
							String string = split[0];
							System.out.println("string = " + string);
							Process p = Runtime.getRuntime().exec("cmd /c start " + string);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
			}

			this.add(panel);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
			this.setSize(200, 200);
			this.setIconImage(new ImageIcon("icons\\human-skull.png").getImage());
			this.setTitle("List Of Process Killed");
		}
	}

	public static void main(String[] args) {

	}

}
