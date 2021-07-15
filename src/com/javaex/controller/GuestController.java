package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestBookVo;

@WebServlet("/guest")
public class GuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("guest호출 성공");
		
		//텍스트 인코딩
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		//리스트
		if("list".equals(action)) {
			System.out.println("리스트");
			//리스트 넣기	
			GuestBookDao guestBookDao = new GuestBookDao();
			List<GuestBookVo> guestBookList = guestBookDao.guestbookList();
			
			//어트리뷰트
			request.setAttribute("gList", guestBookList);
			
			//포워드
			WebUtil.forword(request, response, "/WEB-INF/views/guestbook/list.jsp");
			
		}
		//삭제 폼
		else if("dform".equals(action)) {
			System.out.println("삭제 폼");
			
			//포워드
			WebUtil.forword(request, response, "/WEB-INF/views/guestbook/deleteForm.jsp");
			
		}
		//삭제
		else if("delete".equals(action)) {
			System.out.println("삭제");
			
			//파라미터 받아오기
			int iNo = Integer.parseInt(request.getParameter("id"));
			String pw = request.getParameter("password");
			
			//값 넣기
			GuestBookVo gvo = new GuestBookVo(iNo,pw);
			
			GuestBookDao guestBookDao = new GuestBookDao();
			guestBookDao.guestBookDelete(gvo);
			
			//포워드
			WebUtil.redirecr(request, response, "/mysite/guest?action=list");
			
		}
		//등록
		else if("insert".equals(action)) {
			System.out.println("입력");
			
			//파라미터 받아오기
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			//값 넣기
			GuestBookVo guestBookVo = new GuestBookVo(name,password,content);
			GuestBookDao guestBookDao = new GuestBookDao();
			guestBookDao.guestBookInsert(guestBookVo);
			
			//포워드
			WebUtil.redirecr(request, response, "/mysite/guest?action=list");
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
