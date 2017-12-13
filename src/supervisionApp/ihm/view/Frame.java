package supervisionApp.ihm.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

import supervisionApp.ihm.model.ChartModel;
import supervisionApp.ihm.model.IChartModelListener;
import supervisionApp.ihm.model.MyTableModel;



public class Frame extends JFrame {

	private static final long serialVersionUID = 3455953196718941645L;
     
	private int refreshingPeriod = 0;

	public Frame() {

		// Menu bar
		JMenuBar menubar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenuItem menuItemChangeRefreshingPeriod = new JMenuItem("Change refreshing period");
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuItemExit.setIcon(new ImageIcon("C:\\Users\\Kentin\\eclipse-workspace\\ProjetTable\\src\\exit.png"));
		menuItemChangeRefreshingPeriod
				.setIcon(new ImageIcon("C:\\Users\\Kentin\\eclipse-workspace\\ProjetTable\\src\\refresh.png"));

		menuFile.add(menuItemChangeRefreshingPeriod);
		menuFile.addSeparator();
		menuFile.add(menuItemExit);
		menubar.add(menuFile);

		menuItemExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		MyTableModel tableModel = new MyTableModel();

		menuItemChangeRefreshingPeriod.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String refreshingPeriod = JOptionPane.showInputDialog(Frame.this, "Choose your refreshing period (ms)");
				Frame.this.refreshingPeriod = Integer.valueOf(refreshingPeriod);
				tableModel.setRefreshingPeriod(Frame.this.refreshingPeriod);
			}
		});

		setJMenuBar(menubar);
		setTitle("Process Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Table tableExemple = new Table();
		tableExemple.setModel(tableModel);

		JTabbedPane tabPane = new JTabbedPane();

		
		// Launch CPUChartPanel 
		CPUChartPanel cpuChartPanel = new CPUChartPanel();
		ChartModel chartModel = new ChartModel(new IChartModelListener() {
			@Override
			public void dataChanged(XYDataset dataset) {
				JFreeChart chart = cpuChartPanel.getChart();
				XYPlot plot = chart.getXYPlot();
				plot.setDataset(dataset);
			}
		});
		
		chartModel.startMonitoring();
		
		tabPane.addTab("Process Memory", tableExemple);
		tabPane.addTab("Graph", new JPanel());
		tabPane.addTab("CPU", cpuChartPanel);

		InformationPC informationPC = new InformationPC();

		setLayout(new BorderLayout());
		add(informationPC, BorderLayout.NORTH);
		add(tabPane, BorderLayout.CENTER);

		// setContentPane(tabPane);
		setVisible(true);
		setSize(600, 800);
		setIconImage(
				new ImageIcon("C:\\Users\\Kentin\\eclipse-workspace\\ProjetTable\\src\\icon_frame.png").getImage());
		tableModel.startMonitoring();
	}

	public static void main(String[] args) {
		 new Frame();
		 
	}
}
