package supervisionApp;

public class AlertManager {
	
	private String msgProcessStop = "Alerte : un processus ne tourne plus";
	private String msgMemoryFull  = "Alerte : memoire pleine";
	private User user;
	
	/**
	 * @param user
	 */
	public AlertManager(User user) {
		this.user = user;
	}
	
	public void SendAlert() {
		//TODO
	}
	public void ConnectionServerMail() {
		//TODO
	}
	public void CheckIfAlert() {
		//TODO
	}

}
