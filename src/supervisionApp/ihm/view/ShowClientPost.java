package supervisionApp.ihm.view;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

public class ShowClientPost extends JFrame {

	private ArrayList<String> fileNameList = null;

	public ShowClientPost() {
		
		if(fileNameList == null) {
			fileNameList = ArrayList<String>();
		}
		 String dirName = "C:\\client_connected\\";
		 File dir = new File(dirName);
		File[] f = dir.listFiles();
		int x = 0;
		for (int i = 0 ; i < f.length ; i++) {
		  if (f[i].isFile()) {
		    x++;
		    fileNameList(f[i]);
		  }
		}
		
	}

	public static void main(String[] args) {
		new ShowClientPost();
	}
}
