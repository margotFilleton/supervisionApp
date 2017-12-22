package supervisionApp.ihm.view;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ProcessKilledFrame extends JFrame {
	
	private static final long serialVersionUID = -6000638411036469691L;
	
	private JPanel panel = null; 
	private JTextArea textArea = null;
	
	public ProcessKilledFrame(List<String> list) {
		
		textArea.setText("Process killed \n");
		for(String str : list) {
			String oldTxt = textArea.getText();
			textArea.setText(oldTxt + "str \n");
		}

		panel = new JPanel();
		panel.add(textArea);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(200, 200);
		this.setTitle("List Of Process Killed");
	}
	

	public static void main(String[] args) {

	}

}
