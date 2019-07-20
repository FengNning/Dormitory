package cn.kgc.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;

//连接数据库的工具类
public class DBUtil {
	// 数据库连接池对象
	private static BasicDataSource ds;
	
	//类加载的时候,初始化连接池
	static {
		Properties p = new Properties();
		try {
			p.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			String driver = p.getProperty("driver");
			String url = p.getProperty("url");
			String username = p.getProperty("user");
			String password = p.getProperty("pwd");
			// 连接池的最大连接
			int maxactive = Integer.parseInt(p.getProperty("maxactive"));

			// 初始化连接池
			ds = new BasicDataSource();
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			ds.setMaxActive(maxactive);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获得连接
	public static Connection getConnection() throws SQLException {
		/*
		 * 设置的最大等待时间决定。在等待过程中一旦有可用连接。那么连接池会立刻返回。若等待 时间经过依然没有可用连接时，该方法抛出超时异常
		 */
		Connection conn = ds.getConnection();
		return conn;
	}

	public static void main(String[] args) throws SQLException {
		Connection conn = DBUtil.getConnection();
		if (conn!=null) {
			System.out.println(conn.toString());
			System.out.println("数据库连接成功");
		}
	}
}
