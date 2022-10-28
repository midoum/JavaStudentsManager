import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connexion {
	public static Connection conn;
	String req,user,pass;
	public Connexion() {
		
		
		 conn=null;
		req="CREATE TABLE IF NOT EXISTS notes (id INT( 10 )\r\n"
				+ "NOT NULL PRIMARY KEY AUTO_INCREMENT, nom\r\n"
				+ "VARCHAR(200) NOT NULL, email VARCHAR(200) NOT\r\n"
				+ "NULL UNIQUE, securite VARCHAR(200), reseau\r\n"
				+ "VARCHAR(200), Java VARCHAR(200), php\r\n"
				+ "VARCHAR(200), android VARCHAR(200), sgbd\r\n"
				+ "VARCHAR(200), math VARCHAR(200), francais\r\n"
				+ "VARCHAR(200), anglais VARCHAR(200), pfe\r\n"
				+ "VARCHAR(200))\r\n"
				+ "";
		
		//Déclarations des identifications de la base
		user="root";
		pass="";
		//test si la connexion existe
		if (conn !=null) {
			System.out.println("Already connected");
			
		}else {
			//connexion a la base
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");  
		conn=DriverManager.getConnection("jdbc:mysql://localhost/myfaculty",user,pass);
		PreparedStatement stmt = conn.prepareStatement(req);
		stmt.executeUpdate();
		
		
		System.out.println("Connexion");
		}catch (Exception e ) {
			System.out.println(e);
		}
		}
		
	}
	public void createdatabase() {
		PreparedStatement stmt;
		try {
			stmt = conn .prepareStatement(req);
			stmt.executeUpdate();
			System.out.println("Database created");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public Connection getSession() {
		return conn;
	}
	

}
