package supervisionApp.ihm.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
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

	private ChartModel chartModel = null;

	private int refreshingPeriod = 0;
	private int refreshingCPUPeriod = 0;

	private boolean showInKo = false;

	public Frame() {

		// Menu bar
		JMenuBar menubar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenu menuPreference = new JMenu("Preference");
		JMenuItem menuItemCPUPeriod = new JMenuItem("Change CPU refreshing period");
		JMenuItem menuItemChangeRefreshingPeriod = new JMenuItem("Change process memory refreshing period");
		JMenuItem menuItemExit = new JMenuItem("Exit");

		JCheckBoxMenuItem menuChangeSizeKo = new JCheckBoxMenuItem("Change size on Ko");

		menuItemExit.setIcon(new ImageIcon("icons\\exit.png"));
		menuItemChangeRefreshingPeriod.setIcon(new ImageIcon("icons\\refresh.png"));
		menuItemCPUPeriod.setIcon(new ImageIcon("icons\\line-chart.png"));

		menuFile.add(menuItemCPUPeriod);
		menuFile.add(menuItemChangeRefreshingPeriod);
		menuFile.addSeparator();
		menuFile.add(menuItemExit);

		menuPreference.add(menuChangeSizeKo);
		menuChangeSizeKo.setSelected(false);

		menubar.add(menuFile);
		menubar.add(menuPreference);

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

		menuItemCPUPeriod.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String refreshingCPUPeriod = JOptionPane.showInputDialog(Frame.this,
						"Choose your CPU refreshing period (ms)");
				Frame.this.refreshingCPUPeriod = Integer.valueOf(refreshingCPUPeriod);
				if (chartModel != null) {
					chartModel.setRefreshingCPUPeriod(Frame.this.refreshingCPUPeriod);
				}
			}
		});

		menuChangeSizeKo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showInKo = menuChangeSizeKo.isSelected();
				tableModel.setShowOnKo(showInKo);
			}
		});

		setJMenuBar(menubar);
		setTitle("Process Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Table tableExemple = new Table();
		tableExemple.setModel(tableModel);

		Map<String, String> mapNomTaille = tableModel.getMapNomTaille();

		ChartPie chartPie = new ChartPie(mapNomTaille);
		JPanel createDemoPanel = chartPie.createChartPanel();

		JTabbedPane tabPane = new JTabbedPane();

		// Launch CPUChartPanel
		CPUChartPanel cpuChartPanel = new CPUChartPanel();
		chartModel = new ChartModel(new IChartModelListener() {
			@Override
			public void dataChanged(XYDataset dataset) {
				JFreeChart chart = cpuChartPanel.getChart();
				XYPlot plot = chart.getXYPlot();
				plot.setDataset(dataset);
			}
		});

		chartModel.startMonitoring();

		InformationPC informationPC = new InformationPC();

		tabPane.addTab("Process Memory", tableExemple);
		tabPane.setIconAt(0, (new ImageIcon("icons\\chip.png")));

		tabPane.addTab("Graph", createDemoPanel);
		tabPane.setIconAt(1, (new ImageIcon("icons\\bar-chart.png")));
		tabPane.addTab("CPU", cpuChartPanel);
		tabPane.setIconAt(2, (new ImageIcon("icons\\line-chart.png")));
		tabPane.addTab("Informations System", informationPC);
		tabPane.setIconAt(3, (new ImageIcon("icons\\computer.png")));

		setLayout(new BorderLayout());
		add(tabPane, BorderLayout.CENTER);

		// setContentPane(tabPane);
		setVisible(true);
		setSize(600, 800);
		setIconImage(new ImageIcon("icons\\icon_frame.png").getImage());
		tableModel.startMonitoring();
	}

	public static void main(String[] args) {
		new Frame();
	}
}
