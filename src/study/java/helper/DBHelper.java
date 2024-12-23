package study.java.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
	private static DBHelper current;
	
	public static DBHelper getInstance(){
		if(current == null) {
			current = new DBHelper();
		}
		return current;
	}
	public static void freeInstance(){
		current = null;
	}
	private DBHelper(){}
	

	private static final String db_hostname = "localhost";
	private static final int db_portnumber = 3306;
	private static final String db_database = "mydb";
	private static final String db_charset = "utf8";
	private static final String db_username = "root";
	private static final String db_password = "root";
	
	private static Connection conn = null;
	
	public Connection open() {
		if (conn == null) {
			String urlFormat ="jdbc:mysql://%s:%d/%s?&characterEncoding=%s";
			String url = String.format(urlFormat, db_hostname, db_portnumber, db_database , db_charset);
			
			
			try {

				Class.forName("com.mysql.jdbc.Driver");

				conn = DriverManager.getConnection(url, db_username, db_password);
			
				System.out.println("=DATEBASE CONNECT SUCCESS=");
			
			} catch (ClassNotFoundException e) {
				System.out.println("=DATEBASE CONNECT FAIL=");
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				System.out.println("=DATEBASE CONNECT FAIL=");
				System.out.println(e.getMessage());
			}
		}
		return conn;
		}
	
	public void close() {
		if(conn != null) {
			try {
				conn.close();
				System.out.println("=DATEBASE DISCONNECT SUCCESS=");
			} catch (SQLException e) {
				System.out.println("=DATEBASE DISCONNECT FAIL=");
				System.out.println(e.getMessage());
			}
		}
	
	
		
	}
	
}
