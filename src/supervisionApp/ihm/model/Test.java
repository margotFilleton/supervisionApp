package supervisionApp.ihm.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Test {

	private ArrayList<String> processOtherPC = null;

	private ArrayList<String> getTaskList() {
		try {
			if (processOtherPC == null) {
				processOtherPC = new ArrayList();
			}

			processOtherPC.clear();
			Process exec = Runtime.getRuntime().exec(
					"C:\\Users\\Kentin\\Desktop\\SnmpWalk.exe -r:192.168.20.11 -c:pro -os:.1.3.6.1.2.1.25.4.2.1.2\r\n");

			InputStream inputStream = exec.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.contains(".exe")) {
					processOtherPC.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return processOtherPC;
	}

	public static void main(String[] args) {
		Test test = new Test();
		test.getTaskList();
	}
}
