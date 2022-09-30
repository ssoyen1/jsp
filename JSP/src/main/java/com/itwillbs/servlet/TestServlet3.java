package com.itwillbs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.test.Person;
import com.itwillbs.test.Phone0;

@WebServlet("/test3")
public class TestServlet3 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// [/test3] 가상주소 사용하여 실행
		System.out.println("TestServlet3 - doGet() 호출");

		// Person 객체 정보 생성 후 저장해서 전달

		

		// Person - com.itwill.test 패키지에 생성됨
		
		// 이름, 나이, 휴대폰
		// 휴대폰 - 모델명, 전화번호
		
		
		// "김학생" 20 아이폰14 010-1234-1234
		Phone0 iPhone = new Phone0();
		iPhone.setModel("아이폰14");
		iPhone.setTel("010-1234-1234"); // 밑에 담으려면 따로 객체 생성을 해줘야한다.
		
		
		Person kim = new Person();
		 //담으려면 따로 객체 생성을 해줘야한다.
		
		kim.setName("김학생");
		kim.setAge(20);
		kim.setP(iPhone);//담으려면 따로 객체 생성을 해줘야한다.
		
		// request 영역 저장 (자바코드지만 jsp의 request,respond 받아쓸 수 있음)
		request.setAttribute("Person", kim); //phone은 이미 person에 phone 저장되어있으므로 따로 보낼필요 X
		
		// ./el/Beans.jsp 이동 후 출력(jsp/el)
		RequestDispatcher dis = 
				request.getRequestDispatcher("./el/Beans.jsp");
				
		dis.forward(request, response); // 가져갈 수 있도록
		
		
	}

	
	
}
