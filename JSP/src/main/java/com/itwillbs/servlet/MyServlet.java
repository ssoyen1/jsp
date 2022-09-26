package com.itwillbs.servlet;
//0922
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/itwill")
public class MyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" doGet() 메서드 호출");
		System.out.println(" GET방식으로 주소 호출하면 실행되는 메서드");
		
		response.getWriter().append("<h1> Hello! </h1>");//파일다운로드
		
	}

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" doPost() 메서드 호출");
		System.out.println(" POST방식으로 주소 호출하면 실행되는 메서드");
		
		
	}
	// extends HttpServlet 을 상속하지 않으면 서블릿이라 할 수 없지만
	// 상속받으면 그때부터 서블릿이라 할 수 있음.
}
