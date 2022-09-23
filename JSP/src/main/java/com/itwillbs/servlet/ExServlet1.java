package com.itwillbs.servlet;
//0923

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 임포트한다 = 그것에 해당하는 class가 생긴다는 의미(매번 만들면 귀찮으므로 임포트로 하는 것!)

//class HttpServlet {

//}

			//서블릿 2.3 이전
			
			
			public class ExServlet1 extends HttpServlet{// 가져다쓰고싶으면 상속하면 됨. HttpServlet을 상속받았으므로 ExServlet1도 서블릿이다.
														// (간혹)HttpServlet 임포트 안될때가 있음. build path에 Server runtime이 없을 경우.
			
				// http://localhost:8088/JSP/ex1 (포트번호) (ctrl누른채로 마우스오버)
				
				
				//오버라이딩 메서드 (alt + shife + s -> v / doGet, doPost하기)
				@Override
				protected void doGet(HttpServletRequest request, 
						HttpServletResponse response) throws ServletException, IOException {
					
					
					
					System.out.println("doGET() 호출!"); //(출력)doGET() 호출!
					doPost(request, response);
				}
				
				
				@Override
				protected void doPost(HttpServletRequest request, 
						HttpServletResponse response) throws ServletException, IOException {
					System.out.println("doPOST() 호출!"); //(출력)doPOST() 호출!
					
				} 
				
	

}
