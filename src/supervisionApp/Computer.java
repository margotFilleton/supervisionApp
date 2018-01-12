package supervisionApp;

import java.util.ArrayList;
import java.util.List;

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
