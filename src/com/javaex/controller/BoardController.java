package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("board 호출성공");

		// 텍스트 인코딩
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		//리스트
		if("list".equals(action)) {
			System.out.println("리스트");
			//담기
			BoardDao boardDao = new BoardDao();
			List<BoardVo> boardList = boardDao.boardList();
			//어트리뷰트
			request.setAttribute("bList", boardList);
			//포워드
			System.out.println("포워드");
			WebUtil.forword(request, response, "/WEB-INF/views/board/list.jsp");
			
		}//삭제
		else if("delete".equals(action)) {
			System.out.println("삭제");
			
			int userNo = Integer.parseInt(request.getParameter("userNo"));
			
			BoardDao boardDao = new BoardDao();
			boardDao.boardDelete(userNo);
			
			System.out.println("삭제완료");
			System.out.println("포워드");
			WebUtil.redirecr(request, response, "/mysite/board?action=list");
			
		}//글쓰기 폼
		else if("writeForm".equals(action)) {
			System.out.println("글쓰기폼");
			
			WebUtil.forword(request, response, "/WEB-INF/views/board/writeForm.jsp");
			
		}else if("write".equals(action)) {
			System.out.println("글쓰기");
			
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUeser");
			
			BoardDao boardDao = new BoardDao();
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int userNo = authUser.getNo();
			
			BoardVo boardVo = new BoardVo(title, content, userNo);
			
			boardDao.boardWrite(boardVo);
			
			WebUtil.redirecr(request, response, "/mysite/board?action=list");
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
