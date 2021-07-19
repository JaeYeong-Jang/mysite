package com.javaex.dao;

import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

public class DaoTest {

	public static void main() {

		UserVo userVo = new UserVo("aaa", "1234", "장재영", "male");

		UserDao userDao = new UserDao();
		userDao.userInsert(userVo);
		
		BoardDao boardDao = new BoardDao();
		System.out.println(boardDao.boardList());
		
		System.out.println("1234");
		
	}
}
