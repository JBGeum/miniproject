package com.stone.springmvc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//117p
public class JDBCUtil {
	public static Connection getConnection() {
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 return DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/diarybbs?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC", // DB URL
//	                    "root", "1234");
	                    "user1", "1234"); 
			 }catch (Exception e) {
				 e.printStackTrace();
				 System.out.println("커넥션 실패");
			}
		return null;
	}
	
	public static void close(PreparedStatement pstmt) {
		if(pstmt != null) {	
			try {
				if(!pstmt.isClosed()) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				pstmt = null;
			}
		}
	}		
	
	// insert 시 close 용
	public static void close(PreparedStatement pstmt, Connection conn) {
		if(pstmt != null) {	
			try {
				if(!pstmt.isClosed()) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				pstmt = null;
			}
		if(conn != null) {	
			try {
				if(!conn.isClosed()) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}		

	}
	
	//select 시 close 용
	public static void close(ResultSet rs,PreparedStatement pstmt, Connection conn) {
		if(rs != null) {	
			try {
				if(!rs.isClosed()) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		if(pstmt != null) {	
			try {
				if(!pstmt.isClosed()) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				pstmt = null;
			}
		}
		if(conn != null) {	
			try {
				if(!conn.isClosed()) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
		
	}

}


