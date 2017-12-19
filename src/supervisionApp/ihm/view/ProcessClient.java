package supervisionApp.ihm.view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

public class ProcessClient extends JFrame {

	private static final long serialVersionUID = 2528294466135748509L;

	private static String SEPARATOR = ":";
	private Map<String, String> mapAppliAndTaille = null;
	private Map<String, String> mapServiceAndPID = null;

	public ProcessClient(String fileName) {
		readClientFile(fileName + ".txt");
	}

	private void readClientFile(String fileName) {
		if (mapAppliAndTaille == null) {
			mapAppliAndTaille = new HashMap<String, String>();
			mapServiceAndPID = new HashMap<String, String>();
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("C:\\client_connected\\" + fileName));
			String line = null;
			try {
				while ((line = br.readLine()) != null) {
					System.out.println("line = " + line);
					String[] split = line.split(SEPARATOR);

					String appliRun = split[0].trim();
					String PID = split[1].trim();
					String service = split[3].trim();
					String taille = split[4].trim();

					System.out.println("appliRun = " + appliRun);
					System.out.println("PID = " + PID);
					
					System.out.println("service = " + service);
					System.out.println("taille = " + taille);
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
