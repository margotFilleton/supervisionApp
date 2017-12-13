package supervisionApp;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	
	private String url;
	private User user;
	private String userDB;
	private String passwordDB;
	private Connection connection;
	private boolean isUserConnected;
	private boolean isConnectedToDB;
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * @return true if the user is connected
	 */
	public boolean isUserConnected() {
		return isUserConnected;
	}
	
	/**
	 * @return true if the connection to the database is open
	 */
	public boolean isConnectedToDB() {
		return isConnectedToDB;
	}
	

	/**
	 * @param url : data base url
	 * @param passwordDB : password of the database
	 */
	public ConnectionManager(String url,String userDB, String passwordDB) {
		this.url = url;
		this.user = null ;
		this.userDB = userDB;
		this.passwordDB = passwordDB;
		this.isUserConnected = false;
		this.isConnectedToDB = false;
	}

	/**
	 * Connect to database
	 * @return true if success
	 */
	public boolean ConnectDB() {
		
		try {
  	      Class.forName("com.mysql.jdbc.Driver");
  	      System.out.println("Driver O.K.");
  	      
  	      connection = DriverManager.getConnection(url, userDB, passwordDB);
  	      isConnectedToDB = true;
  	      System.out.println("Connexion effective !");         
  	      return true;
  	      
  	    } catch (Exception e) {
  	      e.printStackTrace();
  	      isConnectedToDB = false;
  	      return false;
  	    }    

	}
	
	/**
	 * Disconnect to database
	 * @return true if success
	 */
	public boolean DisconnectDB() {
		//TODO Disconnect 		
		//IF SUCCESS :
		//isConnectedToDB = false;
		//return true;
		
		//ELSE :
		//isConnectedToDB = true;
		return false;
	}
	
	/**
	 * Connect user with Id and password
	 * @param id
	 * @param password
	 * @return true if success
	 */
	public boolean ConnectUser(String id, String password) {
		//TODO connection 		
		//IF SUCCESS :
		//user = new User(firstname,lastname,mail,false);	
		//isUserConnected = true;
		//return true;
		
		//ELSE :
		//isUserConnected = false;
		return false;
	}
	
	/**
	 * Disconnect User
	 */
	public void DisconnectUser() {		
		user = null;
		isUserConnected = false;
	}

}
