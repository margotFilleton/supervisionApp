package supervisionApp;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import supervisionApp.User;

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

		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isConnectedToDB = false;
		return true;
	}
	
	/**
	 * Connect user with Id and password
	 * @param id
	 * @param cs
	 * @return true if success
	 */
	public boolean ConnectUser(String id, char[] password) {
		boolean result = false;
		try {
			Statement statement = connection.createStatement();
			
			ResultSet resultat2 = statement.executeQuery("SELECT * FROM grain_de_sel");
			String grain = null;
			
			
			while ( resultat2.next()) {
				grain =  resultat2.getString("grain");
			}
			
			ResultSet resultat = statement.executeQuery("SELECT * FROM user");
			
			
			while ( resultat.next() ) {

			    String nom = resultat.getString( "nom" );

			    String prenom = resultat.getString( "prenom" );
			    
			    String passwordStr = resultat.getString( "password" );
			    
			    String profil = resultat.getString( "profil" );

			    String adresse_mail = resultat.getString("adresse_mail");

			    String pwd = new String(password);
			    pwd = pwd + grain;

			    MessageDigest md = MessageDigest.getInstance("MD5");
			    byte[] thedigest = md.digest(pwd.getBytes("UTF-8"));

		        //convertir le tableau de bits en une format hexadécimal - méthode 1
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < thedigest.length; i++) {
		         sb.append(Integer.toString((thedigest[i] & 0xff) + 0x100, 16).substring(1));
		         
		        }
		        String sbStr = new String(sb);
			    
			    /* Traiter ici les valeurs récupérées. */
			    if ((adresse_mail.equals(id)) && (passwordStr.equals(sbStr)))
			    {
			    	System.out.println("Connecté avec l'adresse : " + id + " !");
			    	
			    	if(profil.equals("administrateur"))
			    	{
			    		System.out.println("admin");
			    		user = new User(prenom,nom,adresse_mail,true);
			    		isUserConnected = true;
			    		result = true;
			    	}
			    	else
			    	{
			    		System.out.println("user");
			    		user = new User(prenom,nom,adresse_mail,false);
			    		isUserConnected = true;
			    		result = true;
			    	}
			    }
			    else
			    {
			    	//System.out.println("Nope");
			    }
			    
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//ELSE :
		//isUserConnected = false;
		return result;
	}
	
	/**
	 * Disconnect User
	 */
	public void DisconnectUser() {		
		user = null;
		isUserConnected = false;
	}

}
