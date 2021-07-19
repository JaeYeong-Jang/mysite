package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;

public class BoardDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// 데이터베이스 연결
	private void getconnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			// 3. SQL문 준비 / 바인딩 / 실행

			// 4.결과처리

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 자원 정리
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

	// 리스트
	public List<BoardVo> boardList() {

		List<BoardVo> boardList = new ArrayList<BoardVo>();
		this.getconnection();

		try {

			String query = "";

			query += "select bo.no, ";
			query += "	     bo.title, ";
			query += "       us.name, ";
			query += "		 bo.hit, ";
			query += "       bo.reg_date, ";
			query += "       bo.user_no ";
			query += "from users us, board bo ";
			query += "where us.no = bo.user_no ";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 결과처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String name = rs.getString("name");
				int hit = rs.getInt("hit");
				String date = rs.getString("reg_date");
				int bno = rs.getInt("user_no");

				BoardVo boardVo = new BoardVo(no, title, name, hit, date, bno);
				boardList.add(boardVo);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.Close();
		return boardList;
	}

	// 삭제
	public void boardDelete(int userNo) {

		this.getconnection();
		
		int count = 0;

		try {
			String query = "";

			query += "delete from board ";
			query += "where user_no = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, userNo);
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건 삭제되었습니다.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.Close();

	}
	
	//글쓰기
	public void boardWrite(BoardVo boardVo) {
		
		this.getconnection();
		
		int count = 0;
		
		try {
			
		String query = "";
		
		query += "insert into board ";
		query += "values(seq_board_no.nextval,?,?,0,sysdate,?) ";
		
		
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getUserNo());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건 등록되었습니다.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.Close();
		
	}

}
