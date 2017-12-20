package supervisionApp.ihm.view;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClientPCInformationPanel extends JPanel {

	private static final long serialVersionUID = 1617430828874825649L;

	private JLabel message = new JLabel();
	private JLabel systemName = new JLabel();
	private JLabel systemVersion = new JLabel();

	private JLabel hostName = new JLabel();
	private JLabel ipAdress = new JLabel();
	private JLabel processorId = new JLabel();
	private JLabel processorArchitect = new JLabel();
	private JLabel macAdress = new JLabel();

	private List<String> infoPC = null;

	public ClientPCInformationPanel(List<String> infoPC) {
		this.infoPC = infoPC;
	}

	public void getAllInfo() {
		for (String str : infoPC) {
			System.out.println("str = " + str);
		}
	}
}
