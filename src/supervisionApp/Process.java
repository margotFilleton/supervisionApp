package supervisionApp;

public class Process {

	private String name;
	private float CPU;
	private float memory;
	private float disk;
	private String PID;
	private boolean isStart;
	private float size;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the cPU
	 */
	public float getCPU() {
		return CPU;
	}

	/**
	 * @return the memory
	 */
	public float getMemory() {
		return memory;
	}

	/**
	 * @return the disk
	 */
	public float getDisk() {
		return disk;
	}

	/**
	 * @return the PID
	 */
	public String getPID() {
		return PID;
	}

	/**
	 * @return the isStart
	 */
	public boolean isStart() {
		return isStart;
	}

	/**
	 * @return the size
	 */
	public float getSize() {
		return size;
	}

	/**
	 * @param isStart
	 *            the isStart to set
	 */
	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	/**
	 * @param name
	 * @param CPU
	 * @param memory
	 * @param disk
	 * @param PID
	 * @param isStart
	 */
	public Process(String name, float CPU, float memory, float disk, String PID, boolean isStart) {
		this.name = name;
		this.CPU = CPU;
		this.memory = memory;
		this.disk = disk;
		this.PID = PID;
		this.isStart = isStart;
	}

	public Process(String name, String processSize, String PID, boolean isStart) {
		this.name = name;
		//this.size = processSize;
		this.isStart = isStart;
	}

	public void killProcess() {
		// TODO

		isStart = false;
	}

	public void restartProcess() {
		// TODO

		isStart = true;
	}
}
