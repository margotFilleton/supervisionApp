package supervisionApp.ihm.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class JTabbePanelData extends JTabbedPane {

	
	public void initJTabbePanelData() {
		setAllTabbed();		
		setBorder(null);
	}
	
	public void addMyTab(String name, Component c){
		this.add(name, c);
		initJTabbePanelData();
	}

	@Override
	public void setSelectedComponent(Component c) {	
		// TODO Auto-generated method stub
		super.setSelectedComponent(c);
		int selectedIndex = getSelectedIndex();
		setForegroundAt(selectedIndex, MyColor.blue);
		setBackgroundAt(selectedIndex, Color.white);
	}

	@Override
	public void setSelectedIndex(int index) {
		// TODO Auto-generated method stub
		super.setSelectedIndex(index);
		setAllTabbed();
		setForegroundAt(index, MyColor.blue);
		setBackgroundAt(index, Color.white);
	}

	private void setAllTabbed() {
		int tabCount = getTabCount();
		for (int i = 0; i < tabCount; i++) {
			setForegroundAt(i, Color.white);
			setBackgroundAt(i, MyColor.bgColor);
		}
		int index = super.getSelectedIndex();
		setForegroundAt(index, MyColor.blue);
		setBackgroundAt(index, Color.white);
	}

	public static void main(String[] args) {
	
	/*	UIManager.put("TabbedPane.selected", Color.white);
		
		JFrame jFrame = new JFrame("TabbedPane");
		JTabbePanelData tabs = new JTabbePanelData();
		tabs.addTab("tab1");
		tabs.addTab("tab2");
		tabs.addTab("tab3");
		jFrame.setContentPane(tabs);
		jFrame.setSize(300, 300);
		jFrame.setVisible(true);*/

	}

}
