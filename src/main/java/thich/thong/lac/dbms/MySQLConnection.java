package thich.thong.lac.dbms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import thich.thong.lac.util.LoadObject;

public class MySQLConnection {
	public static Properties DB_CONFIG;
	// JDBC driver name and database URL
	static String JDBC_DRIVER = null;
	static String DB_URL = null;

	// Database credentials
	public static String BD_USER = "";
	public static String BD_PWD = "";

	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;

	public MySQLConnection() throws IOException {
		DB_CONFIG = LoadObject.loading_config_sys("database.properties");

		JDBC_DRIVER = DB_CONFIG.getProperty("mysql.jdbc.driver");
		DB_URL      = DB_CONFIG.getProperty("mysql.db.url");
		
		BD_USER     = DB_CONFIG.getProperty("mysql.db.user");
		BD_PWD      = DB_CONFIG.getProperty("mysql.db.pwd");
	}

	public static ResultSet executeQuery(String myQueries) {
		try {
			
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, BD_USER, BD_PWD);

			stmt = conn.createStatement();
			rs = stmt.executeQuery(myQueries);
			return rs;
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void close() {
		try {
			if (rs != null) {
				rs.close();
			}

			if (stmt != null) {
				stmt.close();
			}

			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {

		}
	}

	public static void executeUpdate(String myQueries) {
		try {
			
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, BD_USER, BD_PWD);

			stmt = conn.createStatement();
			stmt.executeUpdate(myQueries);

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println(myQueries);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}