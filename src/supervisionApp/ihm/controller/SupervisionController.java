package supervisionApp.ihm.controller;

import java.util.List;

import supervisionApp.User;

public class SupervisionController {

	private User user;
	
	private List<String> processKilled;

	public SupervisionController() {

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

}
