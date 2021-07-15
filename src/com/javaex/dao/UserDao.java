package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// DB연결
	private void getconnection() {

		try {

			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 자원정리
	private void Close() {

		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// insert
	public int userInsert(UserVo userVo) {

		int count = 0;

		this.getconnection();

		try {
			String query = "";

			query += "insert into users ";
			query += "values(seq_user_no.nextval, ?, ?, ?, ?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPw());
			pstmt.setString(3, userVo.getName());
			pstmt.setString(4, userVo.getGender());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.Close();

		return count;
	}

	// modify
	public void userModify(UserVo userVo) {

		this.getconnection();

		try {
			String query = "";

			query += "update users ";
			query += "set name = ?, ";
			query += "    password = ?, ";
			query += "    gender = ? ";
			query += "where no = ? ";

			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userVo.getName());
			pstmt.setString(2, userVo.getPw());
			pstmt.setString(3, userVo.getGender());
			pstmt.setInt(4, userVo.getNo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.Close();

	}

	public UserVo getUser(UserVo userVo) {

		UserVo uVo = null;

		this.getconnection();

		try {
			String query = "";

			query += "select no, ";
			query += "		 name, ";
			query += "       id ";
			query += "from users ";
			query += "where id = ? ";
			query += "	    and password = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPw());

			rs = pstmt.executeQuery();

			// 결과처리
			while (rs.next()) {

				int no = rs.getInt("no");
				String name = rs.getString("name");
				String id = rs.getString("id");

				// 생성자가 없을 떈 setter이용
				uVo = new UserVo();
				uVo.setNo(no);
				uVo.setName(name);
				uVo.setId(id);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.Close();

		return uVo;

	}

	//

}
