package supervisionApp.ihm.model;

import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class CPUChartModel {

	private IChartModelListener listener;
	private TimeSeriesCollection dataset;
	private TimeSeries series;
	private CPUInformation cpuInformation;
	private boolean started = false;

	private int refreshingCPUPeriod = 500;

	public CPUChartModel(final IChartModelListener listener) {
		this.listener = listener;
		dataset = new TimeSeriesCollection();

		cpuInformation = new CPUInformation();
		series = new TimeSeries("CPU Usage");
		dataset.addSeries(series);
	}

	private void createDataset(boolean isAClient, String cpuClient) {
		if (isAClient) {
			cpuClient = cpuClient.replaceAll(",", ".");
			double cpuValue = Double.valueOf(cpuClient);
			series.addOrUpdate(new Second(), cpuValue);
			listener.dataChanged(dataset);
		} else {
			String myCPU = cpuInformation.getMyCPU();
			myCPU = myCPU.replaceAll(",", ".");
			double cpuValue = Double.valueOf(myCPU);

			series.addOrUpdate(new Second(), cpuValue);
			listener.dataChanged(dataset);
		}

	}

	public void startMonitoring(boolean isAClient, String cpuClient) {
		if (!started) {
			started = true;
			Thread thread = new Thread() {
				public void run() {
					while (started) {
						try {
							Thread.sleep(refreshingCPUPeriod);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						createDataset(isAClient, cpuClient);
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

	public int getRefreshingCPUPeriod() {
		return refreshingCPUPeriod;
	}

	public void setRefreshingCPUPeriod(int refreshingCPUPeriod) {
		this.refreshingCPUPeriod = refreshingCPUPeriod;
	}
}
