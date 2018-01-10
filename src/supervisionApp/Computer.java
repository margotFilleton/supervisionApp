package supervisionApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import supervisionApp.ihm.controller.SupervisionController;
import supervisionApp.ihm.model.CPUInformation;

public class Computer {

	CPUInformation cpuInformation = null;

	private List<Process> processList;
	private List<String> processKilledList;
	private long memoryUsed;
	private long memoryMax;

	public Computer(SupervisionController controller) {

		processList = new ArrayList<>();
		processKilledList = controller.getListProcessKilled();

		Map<String, String> mapNomTaille = controller.getMapNomTaille();
		if (mapNomTaille != null) {
			for (Map.Entry<String, String> entry : mapNomTaille.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				Float size = Float.valueOf(value);
				processList.add(new Process(key, size, true));
			}
		}else {
			System.out.println("map null");
		}

		long freeMemory = Runtime.getRuntime().freeMemory();
		memoryMax = Runtime.getRuntime().totalMemory();

		memoryUsed = memoryMax - freeMemory;
	}

	/**
	 * @return the processList
	 */
	public List<Process> getProcessList() {
		return processList;
	}

	/**
	 * @return the percentageCPU
	 */
	public double getPercentageCPU() {
		if (cpuInformation == null) {
			cpuInformation = new CPUInformation();
		}
		String myCPU = cpuInformation.getMyCPU();
		myCPU = myCPU.replaceAll(",", ".");
		double percentageCPU = Double.valueOf(myCPU);
		
		return percentageCPU;
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
