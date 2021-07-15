package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// user확인
		System.out.println("[UserController]");

		// 텍스트 인코딩(UTF-8)
		request.setCharacterEncoding("UTF-8");

		// action
		String action = request.getParameter("action");

		// 회원가입폼
		if ("joinForm".equals(action)) {
			// joinform확인
			System.out.println("joinForm");

			// 포워드
			WebUtil.forword(request, response, "/WEB-INF/views/user/joinForm.jsp");

		}
		// 회원가입
		else if ("joinOk".equals(action)) {

			// join확인
			System.out.println("joinOk");

			// 회원가입

			// 파라미터 꺼내기
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			// 파라미터 등록 확인
			System.out.println(id + ", " + pw + ", " + name + ", " + gender);

			// vo만들기
			UserVo userVo = new UserVo(id, pw, name, gender);

			System.out.println(userVo);

			// dao.insert(userVo)
			UserDao userDao = new UserDao();
			userDao.userInsert(userVo);

			// 포워드
			WebUtil.forword(request, response, "/WEB-INF/views/user/joinOk.jsp");

		}
		//
		else if ("loginForm".equals(action)) {

			// loginForm확인
			System.out.println("loginForm");

			// 포워드
			WebUtil.forword(request, response, "/WEB-INF/views/user/loginForm.jsp");

		}
		// 로그인
		else if ("login".equals(action)) {
			System.out.println("login");

			String id = request.getParameter("id");
			String pw = request.getParameter("pw");

			System.out.println(id + ", " + pw);

			UserVo userVo = new UserVo(id, pw);

			UserDao userDao = new UserDao();
			UserVo uVo = userDao.getUser(userVo); //getUser를 통해 넣은 id, pw 값에 대한 no, name 을 uVo에 담는다.

			if (uVo != null) {

				System.out.println("로그인 성공");

				// 성공일 때 세션에 저장 --> 실패일 때 작성 해야함
				HttpSession session = request.getSession();
				session.setAttribute("authUser", uVo); // --> request.setattribute();와 다른 곳에 저장된다.
				
				WebUtil.redirecr(request, response, "/mysite/main");
				
			} else {
				
				// 실패일 때
				
				System.out.println("로그인 실패");
				
				WebUtil.redirecr(request, response, "/mysite/user?action=loginForm&result=fail");
				
			}

		}
		// 로그아웃
		else if ("logout".equals(action)) {
			System.out.println("logout");

			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();

			WebUtil.redirecr(request, response, "/mysite/main");

		}	
		//회원정보 수정폼
		else if ("modifyForm".equals(action)) {
			
			//updateForm 확인
			System.out.println("modifyForm");
			
			System.out.println(request.getParameter("no"));
			
			WebUtil.forword(request, response, "/WEB-INF/views/user/modifyForm.jsp");
			
			//
			
		}
		
		//회원정보 수정
		else if("modify".equals(action)) {
			
			//update 확인
			System.out.println("modify");
			
			System.out.println(request.getParameter("no"));
			
			int no = Integer.parseInt(request.getParameter("no"));
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo(no, id, password, name, gender);
			UserDao userDao = new UserDao();
			
			userDao.userModify(userVo);
			
			System.out.println("번호 : " + userVo.getNo());
			System.out.println(userVo);
			
			if(userVo != null) {
				
				HttpSession session = request.getSession();
				session.setAttribute("authUser", userVo);
				
				WebUtil.redirecr(request, response, "/mysite/main");
				
			}
			
		}
	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
