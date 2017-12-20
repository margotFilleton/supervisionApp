package supervisionApp.ihm.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ClientTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 5051404626097853059L;

	private static final String[] columnName = new String[] { "Nom", "PID", "Services", "Taille" };
	private boolean started = false;
	private static List<String> processList = null;

	private boolean isShowOnKo = false;

	private ArrayList<String> listNom = null;
	private ArrayList<String> listPID = null;
	private ArrayList<String> listServices = null;
	private ArrayList<String> listTaille = null;

	private static int RESHING_PERIOD = 500;
	private static String SEPARATOR = ":";

	private String fileName = null;

	private boolean isCheckPCInfo = true;

	private String nbProcess = null;
	private String osName = null;
	private String osVersion = null;
	private String hostName = null;
	private String ipAdress = null;
	private String processId = null;
	private String processArchi = null;
	private String MACAdress = null;

	private List<String> listInformationPC = null;

	private String CPUValue = null;

	public ClientTableModel(String fileName) {
		this.fileName = fileName;
		readData();
	}

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

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("C:\\client_connected\\" + fileName));
			String line = null;
			try {
				while ((line = br.readLine()) != null) {
					String[] split = line.split(SEPARATOR);
					processList.add(line);
					if (line.contains("CPU=")) {
						String[] splitey = line.split("=");
						CPUValue = splitey[1];
					}

					if (split.length == 4) {
						String appliRun = split[0].trim();
						String PID = split[1].trim();
						String service = split[2].trim();
						String taille = split[3].trim();

						listNom.add(appliRun);
						listPID.add(PID);
						listServices.add(service);
						listTaille.add(taille);

						// System.out.println("appliRun = " + appliRun);
						// System.out.println("PID = " + PID);
						//
						// System.out.println("service = " + service);
						// System.out.println("taille = " + taille);
					}

					if (isCheckPCInfo) {
						listInformationPC = new ArrayList<>();
						if (line.contains("nbProcess=")) {
							String[] splitey = line.split("=");
							nbProcess = splitey[1];
							listInformationPC.add(nbProcess);
						} else if (line.contains("osName=")) {
							String[] splitey = line.split("=");
							osName = splitey[1];
							listInformationPC.add(osName);
						} else if (line.contains("osVersion=")) {
							String[] splitey = line.split("=");
							osVersion = splitey[1];
							listInformationPC.add(osVersion);
						} else if (line.contains("hostName=")) {
							String[] splitey = line.split("=");
							hostName = splitey[1];
							listInformationPC.add(hostName);
						} else if (line.contains("ipAdress=")) {
							String[] splitey = line.split("=");
							ipAdress = splitey[1];
							listInformationPC.add(ipAdress);
						} else if (line.contains("processId=")) {
							String[] splitey = line.split("=");
							processId = splitey[1];
							listInformationPC.add(processId);
						} else if (line.contains("processArchi=")) {
							String[] splitey = line.split("=");
							processArchi = splitey[1];
							listInformationPC.add(processArchi);
						} else if (line.contains("MACAdress=")) {
							String[] splitey = line.split("=");
							MACAdress = splitey[1];
							listInformationPC.add(MACAdress);
						}

						System.out.println("MACAdress = " + MACAdress);
						isCheckPCInfo = false;
					}
				}
				this.fireTableDataChanged();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void startMonitoring() {
		if (!started) {
			started = true;
			Thread thread = new Thread() {
				public void run() {
					while (started) {
						try {
							Thread.sleep(5000);
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

	// public void ressourcingManager() {
	// long freeMemory = Runtime.getRuntime().freeMemory();
	// long freeMemory2 = Runtime.getRuntime().totalMemory();
	//
	// System.out.println("freeMemory = " + freeMemory + " totalMemory = " +
	// freeMemory2);
	// }

	public int getRefreshingPeriod() {
		return RESHING_PERIOD;
	}

	public boolean isShowOnKo() {
		return isShowOnKo;
	}

	public void setShowOnKo(boolean isShowOnKo) {
		this.isShowOnKo = isShowOnKo;
	}

	// Getter and setter of all list
	public ArrayList<String> getListNom() {
		return listNom;
	}

	public void setListNom(ArrayList<String> listNom) {
		this.listNom = listNom;
	}

	public ArrayList<String> getListPID() {
		return listPID;
	}

	public void setListPID(ArrayList<String> listPID) {
		this.listPID = listPID;
	}

	public ArrayList<String> getListServices() {
		return listServices;
	}

	public void setListServices(ArrayList<String> listServices) {
		this.listServices = listServices;
	}

	public ArrayList<String> getListTaille() {
		return listTaille;
	}

	public void setListTaille(ArrayList<String> listTaille) {
		this.listTaille = listTaille;
	}

	public String getCpuClient() {
		return CPUValue;
	}
	//
	// public void setCpuClient(String cpuClient) {
	// this.cpuClient = cpuClient;
	// }

	public List<String> getInfoPC() {
		return listInformationPC;
	}
}
