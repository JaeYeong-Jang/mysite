package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;


@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//user확인
		System.out.println("[UserController]");
		
		//텍스트 인코딩(UTF-8)
		request.setCharacterEncoding("UTF-8");
		
		//action
		String action = request.getParameter("action");
		
		//회원가입폼
		if("joinForm".equals(action)) {
			//joinform확인
			System.out.println("joinForm");
			
			//포워드
			WebUtil.forword(request, response, "/WEB-INF/views/user/joinForm.jsp");
			
		}
		//회원가입
		else if("joinOk".equals(action)) {
			
			//join확인
			System.out.println("joinOk");
			
			//회원가입
			
			//파라미터 꺼내기
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			//파라미터 등록 확인
			System.out.println(id + ", " + pw + ", " + name + ", " + gender);
			
			//vo만들기
			UserVo userVo = new UserVo(id,pw,name,gender);
			
			//dao.insert(userVo)
			UserDao userDao = new UserDao();
			userDao.userInsert(userVo);
			
			//포워드
			WebUtil.forword(request, response, "/WEB-INF/views/user/joinOk.jsp");
			
		}
		//
		else if("loginForm".equals(action)) {
			
			//loginForm확인
			System.out.println("loginForm");
			
			//포워드
			WebUtil.forword(request, response, "/WEB-INF/views/user/loginForm.jsp");
			
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
