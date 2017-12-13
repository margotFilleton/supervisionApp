package supervisionApp.ihm.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

	private static final long serialVersionUID = -5831729325464935266L;
	
	private static final String[] columnName = new String[] { "Nom", "PID", "Services", "Taille" };
	private boolean started = false;
	private static List<String> processList = null;

	private ArrayList<String> listNom = null;
	private ArrayList<String> listPID = null;
	private ArrayList<String> listServices = null;
	private ArrayList<String> listTaille = null;
	
	private int refreshingPeriod = 500;

	@Override
	public String getColumnName(int column) {
		String name = "??";
		switch (column) {
		case 0:
			name = columnName[0];
			break;
		case 1:
			name = columnName[1];
			break;
		case 2:
			name = columnName[2];
			break;
		case 3:
			name = columnName[3];
			break;
		}
		return name;
	}

	@Override
	public int getColumnCount() {
		return columnName.length;
	}

	@Override
	public int getRowCount() {
		return processList != null ? processList.size() : 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String value = "";
		switch (columnIndex) {
		case 0:
			value = listNom.get(rowIndex);
			break;
		case 1:
			value = listPID.get(rowIndex);
			break;
		case 2:
			value = listServices.get(rowIndex);
			break;
		case 3:
			value = listTaille.get(rowIndex);
			break;
		default:
			throw new IllegalArgumentException();
		}
		return value;
	}

	private void readData() {
		if (processList == null) {
			processList = new ArrayList<>();
			listNom = new ArrayList<>();
			listPID = new ArrayList<>();
			listServices = new ArrayList<>();
			listTaille = new ArrayList<>();
		}
		processList.clear();
		listNom.clear();
		listPID.clear();
		listServices.clear();
		listTaille.clear();
		try {
			Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
			InputStream inputStream = p.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
					
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					String[] extraireDonnees = extraireDonnees(line);

					if (extraireDonnees.length == 6) {
						processList.add(line);
						listNom.add(extraireDonnees[0]);
						listPID.add(extraireDonnees[1]);
						listServices.add(extraireDonnees[2]);
						listTaille.add(extraireDonnees[4].replace("\u00ff", "") + " " + extraireDonnees[5]);
					}
				}
			}

			Collections.sort(listNom);
			Collections.sort(listPID);
			Collections.sort(listServices);
			Collections.sort(listTaille);
			this.fireTableDataChanged();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] extraireDonnees(String line) {
		StringTokenizer st = new StringTokenizer(line, " ");
		int i = 0;
		String mot[] = new String[st.countTokens()];

		while (st.hasMoreTokens()) {
			mot[i] = st.nextToken();
			i++;
		}
		return mot;
	}

	public void startMonitoring() {
		if (!started) {
			started = true;
			Thread thread = new Thread() {
				public void run() {
					while (started) {
						try {
							Thread.sleep(refreshingPeriod);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						readData();
					}
				};
			};
			thread.start();
		}
	}

	public void stopMonitoring() {
		if (started) {
			started = false;
		}
	}
	
	public void ressourcingManager() {
		long freeMemory = Runtime.getRuntime().freeMemory();
		long freeMemory2 = Runtime.getRuntime().totalMemory();
		
		System.out.println("freeMemory = " + freeMemory + " totalMemory = " + freeMemory2);
	}
	
	public int getRefreshingPeriod() {
		return refreshingPeriod;
	}

	public void setRefreshingPeriod(int refreshingPeriod) {
		this.refreshingPeriod = refreshingPeriod;
	}
}
