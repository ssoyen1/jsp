package com.itwillbs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet; //클래스만들때 슈퍼클래스에서 httpServlet ~ 선택
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



			@WebServlet("/ex2")
			public class ExServlet2 extends HttpServlet {
			
			
				
				// http://localhost:8088/JSP/ex2 (ctrl누른채로 마우스오버)
				@Override
				protected void doGet(HttpServletRequest request, 
						HttpServletResponse response) throws ServletException, IOException {
					System.out.println("doGET() 호출 2.3 이후 !");
				}
	
													//정보: 이름이 [/JSP]인 컨텍스트(=프로젝트)를 다시 로드하는 것을 완료했습니다.

			
			}
