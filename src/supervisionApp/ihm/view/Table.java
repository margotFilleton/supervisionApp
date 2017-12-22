package supervisionApp.ihm.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import org.jdesktop.swingx.JXTable;

import supervisionApp.ihm.controller.SupervisionController;

public class Table extends JPanel {

	private static final long serialVersionUID = -2739124138805812437L;

	private JPopupMenu popupMenu = null;
	private JMenuItem menuItemKillProcess = null;
	private JTable table = null;
	private String processName = null;

	private List<String> listProcessKill = null;

	private SupervisionController supervisionController = null;

	public Table(SupervisionController supervisionController) {
		this.supervisionController = supervisionController;
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		table = new JXTable();
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (supervisionController.getUser() != null) {
					if (supervisionController.getUser().isAdmin()) {
						if (SwingUtilities.isRightMouseButton(e)) {
							if (popupMenu == null) {
								createJPopupMenu();
							}
							int selectedRow = table.getSelectedRow();
							if (selectedRow != -1) {

								processName = (String) table.getValueAt(selectedRow, 0);
								popupMenu.show(e.getComponent(), e.getX(), e.getY());
							} else {
							}
						}
					}
				}
			}
		});
	}

	private void createJPopupMenu() {
		popupMenu = new JPopupMenu();
		menuItemKillProcess = new JMenuItem("Kill process");
		menuItemKillProcess.setIcon(new ImageIcon("icons\\human-skull.png"));

		popupMenu.add(menuItemKillProcess);

		menuItemKillProcess.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("TASKKILL /F /IM " + processName);
					if (listProcessKill == null) {
						listProcessKill = new ArrayList<>();
					}

					listProcessKill.add(processName);
					supervisionController.setListProcessKilled(listProcessKill);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public void setModel(final AbstractTableModel model) {
		table.setModel(model);
	}
}
