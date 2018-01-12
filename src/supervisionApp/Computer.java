package supervisionApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import supervisionApp.ihm.controller.SupervisionController;
import supervisionApp.ihm.model.CPUInformation;

public class Computer {

	private CPUInformation cpuInformation = null;

	private List<Process> processList;
	private List<String> processKilledList;
	private long memoryUsed;
	private long memoryMax;
	private double percentageCPU = 0;
	private AlertManager alert;

	public Computer(SupervisionController controller) {
		alert = controller.getAlert();
		processList = new ArrayList<>();
		processKilledList = controller.getListProcessKilled();
		
		
		

		Thread threadtest = new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					List<Process> processObjectList = controller.getProcessObjectList();
					if (processObjectList.size() > 0) {
						setProcessList(processObjectList);
					}					
				}
			};
		};
		threadtest.start();

		// Map<String, Map<String, String>> mapNomTaillePID =
		// controller.getMapNomTaillePID();
		//
		// if (mapNomTaillePID != null) {
		// for (Map.Entry<String, Map<String, String>> entry :
		// mapNomTaillePID.entrySet()) {
		// String processName = entry.getKey();
		// Map<String, String> mapTaillePID = entry.getValue();
		//
		// for (Entry<String, String> entryMap : mapTaillePID.entrySet()) {
		// String processSize = entryMap.getKey();
		// String processPID = entryMap.getValue();
		//
		// Float processSizeFloat = Float.valueOf(processSize);
		// float cpu = 0;
		// float disk = 0;
		//
		// processList.add(new Process(processName, cpu, processSizeFloat, disk,
		// processPID, true));
		// }
		// }
		// } else {
		// System.out.println("Map = mapNomTaillePID == null");
		// }

		// if (mapNomTaille != null) {
		// for (Map.Entry<String, String> entry : mapNomTaille.entrySet()) {
		// String key = entry.getKey();
		// String value = entry.getValue();
		// Float size = Float.valueOf(value);
		//
		// for (Map.Entry<String, String> myMap : mapNomPID.entrySet()) {
		// String pid = myMap.getValue();
		// String name = myMap.getKey();
		//
		// System.out.println("value = " + key + " value2 = " + name);
		// if (key == name) {
		// System.out.println("key = " + key + " size = " + size + " pid = " + pid);
		// processList.add(new Process(key, size, pid, true));
		// }
		// }
		// }
		// } else {
		// System.out.println("map null");
		// }

		long freeMemory = Runtime.getRuntime().freeMemory();
		memoryMax = Runtime.getRuntime().totalMemory();

		memoryUsed = memoryMax - freeMemory;

		Thread thread = new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (cpuInformation == null) {
						cpuInformation = new CPUInformation();
					}
					String myCPU = cpuInformation.getMyCPU();
					myCPU = myCPU.replaceAll(",", ".");
					setPercentageCPU(Double.valueOf(myCPU));
				}
			};
		};
		thread.start();
	}

	/**
	 * @return the alert
	 */
	public AlertManager getAlert() {
		return alert;
	}

	/**
	 * @param alert
	 *            the alert to set
	 */
	public void setAlert(AlertManager alert) {
		this.alert = alert;
	}

	/**
	 * @return the processList
	 */
	public List<Process> getProcessList() {
		return processList;
	}

	/**
	 * @return the processList
	 */
	public void setProcessList(List<Process> processList) {
		this.processList = processList;
		alert.checkIfProcessStop(this);
	}

	/**
	 * @return the percentageCPU
	 */
	public double getPercentageCPU() {
		return percentageCPU;
	}

	/**
	 * @param percentageCPU
	 *            the percentageCPU to set
	 */
	public void setPercentageCPU(double percentageCPU) {
		this.percentageCPU = percentageCPU;
		alert.checkIfAlertCPU(this);
	}

	/**
	 * @return the memoryUsed
	 */
	public long getMemoryUsed() {
		return memoryUsed;
	}

	/**
	 * @return the memoryMax
	 */
	public long getMemoryMax() {
		return memoryMax;
	}

	/**
	 * @return the processKilled
	 */
	public List<String> getProcessKilled() {
		return processKilledList;
	}

}
