package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	 private static final String URL = "jdbc:mysql://localhost:3306/BANKINGAPP";
	    private static final String USERNAME = "root";
	    private static final String PASSWORD = "72637445";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL,USERNAME,PASSWORD);
	}
	
	public static void closeConnection(Connection con) {
		if(con != null) {
			try {
				con.close();
			}catch(SQLException e) {
				System.out.println("Error Closing Connection"+ e.getMessage());
			}
		}
	}

}
