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
			WebUtil.redirecr(request, response, "./board?action=list");
			
		}//글쓰기 폼
		else if("writeForm".equals(action)) {
			System.out.println("글쓰기폼");
			
			WebUtil.forword(request, response, "/WEB-INF/views/board/writeForm.jsp");
			
		}//글쓰기
		else if("write".equals(action)) {
			System.out.println("글쓰기");
			
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			BoardDao boardDao = new BoardDao();
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int userNo = authUser.getNo();
			System.out.println(userNo);
			BoardVo boardVo = new BoardVo(title, content, userNo);
			
			boardDao.boardWrite(boardVo);
			
			WebUtil.redirecr(request, response, "./board?action=list");
			
		}//읽기
		else if("read".equals(action)) {
			System.out.println("읽기");
			
			BoardVo boardVo = new BoardVo();
			
			int bNo = Integer.parseInt(request.getParameter("no"));
			
			System.out.println(bNo);
			
			BoardDao boardDao = new BoardDao();
			boardVo = boardDao.getWriterInfo(bNo);
			
			request.setAttribute("bVo", boardVo);
			
			WebUtil.forword(request, response, "/WEB-INF/views/board/read.jsp");
			
		}//수정폼
		else if("modifyForm".equals(action)) {
			System.out.println("수정폼");
			
			BoardVo boardVo = new BoardVo();
			
			int bNo = Integer.parseInt(request.getParameter("no"));
			
			BoardDao boardDao = new BoardDao();
			boardVo = boardDao.getWriterInfo(bNo);
			
			request.setAttribute("uVo", boardVo);
			
			WebUtil.forword(request, response, "/WEB-INF/views/board/modifyForm.jsp");
			
		}//수정
		else if("update".equals(action)) {
			System.out.println("수정");
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int uno = Integer.parseInt(request.getParameter("no"));
			
			BoardVo boardVo = new BoardVo(uno, title, content);
			BoardDao boardDao = new BoardDao();
			boardDao.boardUpdate(boardVo);
			
			System.out.println(boardVo);
			
			System.out.println("수정성공");
			
			WebUtil.redirecr(request, response, "./board?action=list");
			
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
