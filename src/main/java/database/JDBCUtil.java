package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection c = null;
		try {
			// đăng ký MySQL với DriverManager
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
			String url = "jdbc:mySQL://localhost:3306/quanlythuvien";
			String username = "root";
			String password = "";
			
			c = DriverManager.getConnection(url, username, password);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return c;
	}
	public static void closeConnection(Connection c) {
		try {
			if(c != null)
			{
				if(!c.isClosed()) {
					c.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
