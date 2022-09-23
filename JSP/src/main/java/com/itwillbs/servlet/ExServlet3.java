package com.itwillbs.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//0923
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// web.xml 매핑과 @WebServlet 매핑이 동일할 경우 에러 발생
// => 매핑이 다를 경우 사용가능하나 권장X

		@WebServlet("/ex4")
		public class ExServlet3 extends HttpServlet {
		
			int cnt; // cnt 변수 선언만 해놓기. init안에 0;하기
			
			
			// http://localhost:8088/JSP/ex3		 // <url-pattern>/ex3</url-pattern>
			// http://localhost:8088/JSP/test.ex3    // <url-pattern>*.ex3</url-pattern> 이렇게 작성하면 여러개의 컨트롤주소를 만들 수 있다.
			// http://localhost:8088/JSP/아이티윌.ex3
			
			@Override
			public void init() throws ServletException {
				// 처음 생성될때에만 ! 그다음부터는 새로고침해도 바로바로 실행만 한다.
				System.out.println(" init : 서블릿 최초 생성할 때 실행");
				System.out.println(" init : 서블릿 객체를 초기화! ");
				cnt = 0;
			}
			
			
			
			@Override
			protected void doGet(HttpServletRequest request, // 왜출력이안되지???????????????????????
					HttpServletResponse response) throws ServletException, IOException {
				System.out.println(" doGet : ExServlet3() 호출!");
				System.out.println(" cnt : "+(++cnt));
				
//				response.getWriter().append("cnt:"+(cnt));
				response.setContentType("test/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.println(" <html> ");
				out.println(" <head></head> ");
				out.println(" <body> ");
				out.println(" <h1>cnt : "+cnt+"</h1>");
				out.println(" </body> ");
				out.println(" </html> ");
				
															 // init : 서블릿 최초 생성할 때 실행
															 // init : 서블릿 객체를 초기화! 
															 // doGet : ExServlet3() 호출!
																 // ->(새로고침할 경우) ExServlet3() 호출! 만 
																 // ->(서버스톱할경우) 서블릿 소멸될 때 실행 ! 
																 //					   서블릿 전부 사용 ! 
			}
		
			
			
			
			@Override
			public void destroy() {
				System.out.println("서블릿 소멸될 때 실행 ! ");
				System.out.println("서블릿 전부 사용 ! ");
			}
		
		
				// doGET() 오버라이딩 + web.xml 매핑 (/ex3)
			
															 // cnt 변수 설정 한 후 실행
																// init : 서블릿 최초 생성할 때 실행
																// init : 서블릿 객체를 초기화! 
																// doGet : ExServlet3() 호출!
																// cnt : 1
															 // 한번 더 방문했을 때 
																//doGet : ExServlet3() 호출!
																//cnt : 2
															 // 서버 재실행 했을 때
																//init : 서블릿 최초 생성할 때 실행
																//init : 서블릿 객체를 초기화! 
																//doGet : ExServlet3() 호출!
															    //cnt : 1
		}
