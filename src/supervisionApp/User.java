package supervisionApp;

public class User {

	private String firstname;
	private String lastname;
	private String mail;
	private boolean isAdmin;
	
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @return the isAdmin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}
	
	/**
	 * @param firstname
	 * @param lastname
	 * @param mail
	 * @param isAdmin
	 */
	public User(String firstname, String lastname, String mail, boolean isAdmin) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.mail = mail;
		this.isAdmin = isAdmin;
	}
	
	
}
