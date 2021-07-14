package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.util.WebUtil;

@WebServlet("/guest")
public class GuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("guest호출 성공");
		
		String action = request.getParameter("action");
		
		//리스트
		if("list".equals(action)) {
			System.out.println("리스트");
			//리스트 넣기	
			
			WebUtil.forword(request, response, "/WEB-INF/views/guestbook/list.jsp");
			
		
		}
		//삭제 폼
		
		//삭제
		
		//등록
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
