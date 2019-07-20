package cn.kgc.dao;

import cn.kgc.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据库连接与关闭工具类。
 * @author 北大青鸟
 */
public class BaseDao {

	protected Connection conn =null;
	protected PreparedStatement pstmt = null;

	public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		// 若结果集对象不为空，则关闭
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 若Statement对象不为空，则关闭
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 若数据库连接对象不为空，则关闭
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 增、删、改操作
	 * @param param sql语句
	 * @param preparedSql 参数数组
	 * @return 执行结果
	 */
	public int exceuteUpdate(String preparedSql, Object... param) {
		int num = 0;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					// 为预编译sql设置参数
					pstmt.setObject(i + 1, param[i]);
				}
			}
			num = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
		return num;
	}
	
	public ResultSet executeQuery(String sql, Object... params) {
        ResultSet rs = null;
        try {
        	conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            rs = pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

	public static void main(String[] args) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn =dbUtil.getConnection();
		if (conn!=null) {
			System.out.println("连接数据库成功!");
		}

	}
}
