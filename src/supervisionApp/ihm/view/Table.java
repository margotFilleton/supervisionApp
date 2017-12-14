package supervisionApp.ihm.view;


import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.jdesktop.swingx.JXTable;


public class Table extends JPanel {

	private static final long serialVersionUID = -2739124138805812437L;
	
	JTable table = null;

	public Table() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		table = new JXTable();
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void setModel(final AbstractTableModel model) {
		table.setModel(model);
	}
}
