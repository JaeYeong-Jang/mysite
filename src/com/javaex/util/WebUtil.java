package com.javaex.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	
	//fields
	
	//constructors
	
	//g/s
	
	//method
	
	//forword
	public static void forword(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
		
	}
	
	public static void redirecr(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		
		response.sendRedirect(url);
		
	}
}
