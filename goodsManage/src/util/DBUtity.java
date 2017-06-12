package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author:李祖林
 * @description:JDBC连接工具类
 * @date:2017年6月8日上午10:12:10
 */
public class DBUtity {
	private static  Properties properties = new Properties();
	private static String driver = null;
	private static String url = null;
	private static String user = null;
	private static String pwd = null;
	static {
		try {
			properties.load(DBUtity.class.getClassLoader().getResourceAsStream("resource/jdbc.properties"));
			driver = properties.getProperty("jdbc.driverClassName");
			url = properties.getProperty("jdbc.url");
			user = properties.getProperty("jdbc.username");
			pwd = properties.getProperty("jdbc.password");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	public static Connection openConnection() throws SQLException {
		return DriverManager.getConnection(url, user, pwd);
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("关闭连接");
			}
		}
	}
}
