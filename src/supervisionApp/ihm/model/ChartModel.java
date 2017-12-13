package supervisionApp.ihm.model;

import java.util.Random;

import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class ChartModel {

	private IChartModelListener listener;
	private TimeSeriesCollection dataset ;
	private TimeSeries series;
	//private Random random;
	private CPUInformation cpuInformation;
	private boolean started = false;

	public ChartModel(final IChartModelListener listener) {
		this.listener = listener;
		dataset = new TimeSeriesCollection();
		
		cpuInformation = new CPUInformation();
		series = new TimeSeries("CPU Usage");
		dataset.addSeries(series);
		//random = new Random();
	}

	private void createDataset() {
		//int nextInt = random.nextInt(100);
		String myCPU = cpuInformation.getMyCPU();
		myCPU = myCPU.replaceAll(",", ".");
		double cpuValue = Double.valueOf(myCPU);
	
		series.addOrUpdate(new Second(), cpuValue);
		listener.dataChanged(dataset);
	}
	
	public void startMonitoring() {
		if (!started) {
			started = true;
			Thread thread = new Thread() {
				public void run() {
					while (started) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						createDataset();
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
}
