package supervisionApp;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

	public static void main(String[] args) {
		ConnectionManager newDBConnection = new ConnectionManager("jdbc:mysql://192.168.20.11:3306/projet","root","password");
		Boolean test = newDBConnection.ConnectDB();
	}

}
