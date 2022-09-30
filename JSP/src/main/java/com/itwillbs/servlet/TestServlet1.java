package com.itwillbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//0926

//	* 서블릿 생성 규칙
//	1) javax.servlet.Servlet 인터페이스 객체를 구현하기
//	2) 1번의 동작을 하기 어려운 경우, javax.servlet.http.HttpServlet 상속하기
//	3) doGet() / doPost()메서드 구현해야함 (오버라이딩) (= HttpServlet에 두개가 정의되어있을것이다.)
//	4) 전달인자로 javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse 사용해야함. --> 웹페이지 구현안됨. 404뜸.
//	5) java.io.IOExcetion, javax.servlet.ServletExcetion을 처리해야함 (예외처리)
//	6) web.xml (배포서술자) 서블릿과 주소(url) 매핑
//		=> servlet 2.3이후에서는 어노테이션으로 대체 @WebServlet()

public class TestServlet1  extends HttpServlet{ //1) 상속받기 - 임폴트
			
			// 2)  alt + shift + s + v 
			// 3) web.xml에서 mapping 하기
			// 4) http://localhost:8088/JSP/test1 (get방식) - 흰 화면(정상적으로 매핑은 되었다.)
			@Override
			protected void doGet(HttpServletRequest request, 
					HttpServletResponse response) throws ServletException, IOException {
				System.out.println("doGet() 호출");
				
				doPost(request, response);
			}
		
			
			@Override
			protected void doPost(HttpServletRequest request, 
					HttpServletResponse response) throws ServletException, IOException {
				System.out.println("doPost() 호출");
			
				
				// request 영역에 정보 저장
				request.setAttribute("cnt", 1000); //request.setAttribute("객체명", 객체);
//				request.setAttribute("cnt", new Integer(1000)); // 기본형 타입을 인티저로 바꾸는 것.
																// 컴파일러 버전이 낮았을 때 사용.(1.3정도일떄)
				
				
				// session 영역에 정보 저장
						// 영역객체를 무시해버리면 영역범위의 크기가 작은 곳에서 큰 곳으로 이동하면서 검색
						// 동일 이름의 속성값이 존재할 경우, 잘못된 데이터 참조
				// cnt - 2000
//				session.setAtt
				HttpSession session = request.getSession(); // 세선정보를 바로쓸 수 없으므로 request 사용해서가져오기
				session.setAttribute("cnt", 2000);
				
				
			
//				//(1)
////				response.getWriter().append("<h1> HTML 메세지 작성하기</h1>");
//				response.setContentType("test/html; charset=UTF-8");
//				
//				//(2)
//				PrintWriter out = response.getWriter();
//				out.print("<h1> HTML 메세지 작성하기 222 </h1>");
//				out.close();
				
				//(3) forward 방식 -  포워딩
				// 	  1. 페이지 이동시 주소 변경X, 화면 변경O 
				//	  2. request 내장객체 정보를 전달 가능 (jsp2폴더 - scope 내용)
//				<jsp:forward > // (사용할 수 없음!) jsp코드이므로 . 여기는 java임 -> 자바코드로 이걸 불러야 함 ->
				RequestDispatcher dis 
					= request.getRequestDispatcher("./el/Attribute.jsp"); // (이동할 주소)  / <jsp:forward > 와 같은의미
																		  // doPost에서 가져오는 request로 하는것
				dis.forward(request, response);
				
																		  // 다 번거로우므로 그냥 jsp와 서블릿 따로 만들어서 연결하기.
			} 
			
			
			
		}
