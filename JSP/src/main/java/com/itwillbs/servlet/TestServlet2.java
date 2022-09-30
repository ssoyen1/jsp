package com.itwillbs.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/test2") //web.xml에 적는거 말고 다른 방법
public class TestServlet2 extends HttpServlet {

	// http://localhost:8088/JSP/test2
	// alt + shift + s + v
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	
		
		
		// 배열
		// String foods
		String[] foods = {"삼겹살", "햄버거", "피자","엄마밥", "파스타"};
		
		
		
		// 배열정보를 request 영역에 저장
		request.setAttribute("foods",foods); //(왼)"foods"라는 이름으로 (오)foods를 위에서 얻어옴
											 // 페이지이동했을 때만 (요청시에만) 동작할 수 있도록 request영역에 담음
		
		
		
		
		
		// 스포츠종류를 ArrayList 저장
//		ArrayList sports = new ArrayList();
		List sports = new ArrayList();
		
		sports.add("축구");
		sports.add("야구");
		sports.add("농구");
		sports.add("배구");
		sports.add("펜싱");
		
		// request 영역 저장
		request.setAttribute("sports", sports);
		
		
		
		// forward방식 이동 - ./el/Arrays.jsp
		RequestDispatcher dis
			= request.getRequestDispatcher("./el/Arrays.jsp"); // 안의 객체 만들어서 이동할 수 있도록한다.
		
		dis.forward(request, response);
		
		
	}
	
	
	

}











