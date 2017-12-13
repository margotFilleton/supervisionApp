package supervisionApp.ihm.model;


import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

public class CPUInformation {

	public String getMyCPU() {
		String myCPU = "";

			try {
				OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
				java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
				double cpu = osBean.getSystemCpuLoad() * 100;
				myCPU = df.format(cpu);

			} catch (Exception e) {
				e.printStackTrace();
			}

		return myCPU;
	}
	
//	public static void main(String[] args) {
//		getMyCPU();
//	}
}