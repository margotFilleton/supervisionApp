package supervisionApp;

public class Process {
	
	private String name;
	private float CPU;
	private float memory;
	private float disk;
	private float network;
	private boolean isStart;
	
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
	 * @return the network
	 */
	public float getNetwork() {
		return network;
	}
	
	/**
	 * @return the isStart
	 */
	public boolean isStart() {
		return isStart;
	}
	/**
	 * @param isStart the isStart to set
	 */
	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}
	
	/**
	 * @param name
	 * @param CPU
	 * @param memory
	 * @param disk
	 * @param network
	 * @param isStart
	 */
	public Process(String name, float CPU, float memory, float disk, float network, boolean isStart) {
		this.name = name;
		this.CPU = CPU;
		this.memory = memory;
		this.disk = disk;
		this.network = network;
		this.isStart = isStart;
	}

	
	public void killProcess() {
		//TODO
		
		isStart = false;
	}
	
	public void restartProcess() {
		//TODO
		
		isStart = true;
	}
}
