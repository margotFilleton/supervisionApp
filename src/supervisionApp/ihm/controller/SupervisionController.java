package supervisionApp.ihm.controller;

import java.util.List;
import java.util.Map;

import supervisionApp.User;

public class SupervisionController {

	private User user;
	private List<String> processKilled = null;
	private List<String> processList = null;

	private Map<String, String> mapNomTaille = null;
	private Map<String, Map<String, String>> mapNomTaillePID = null;

	public SupervisionController(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getListProcessKilled() {
		return processKilled;
	}

	public void setListProcessKilled(List<String> processKilled) {
		this.processKilled = processKilled;
	}

	public List<String> getProcessList() {
		return processList;
	}

	public void setProcessList(List<String> processList) {
		this.processList = processList;
	}

	public Map<String, String> getMapNomTaille() {
		return mapNomTaille;
	}

	public void setMapNomTaille(Map<String, String> mapNomTaille) {
		this.mapNomTaille = mapNomTaille;
	}

	public void setMapNomTaillePID(Map<String, Map<String, String>> mapNomTaillePID) {
		this.mapNomTaillePID = mapNomTaillePID;
	}

	public Map<String, Map<String, String>> getMapNomTaillePID() {
		return mapNomTaillePID;
	}

}
