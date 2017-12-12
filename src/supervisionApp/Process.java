package supervisionApp;

public class Process {
	
	private String name;
	private float CPU;
	private float memory;
	private float disk;
	private float network;
	
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
	 * @param name
	 * @param CPU
	 * @param memory
	 * @param disk
	 * @param network
	 */
	public Process(String name, float CPU, float memory, float disk, float network) {
		this.name = name;
		this.CPU = CPU;
		this.memory = memory;
		this.disk = disk;
		this.network = network;
	}

	public void killProcess() {
		//TODO
	}
	
	public void restartProcess() {
		//TODO
	}
}
