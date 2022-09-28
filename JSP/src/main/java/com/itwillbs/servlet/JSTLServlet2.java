package com.itwillbs.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jstl2")
public class JSTLServlet2 extends HttpServlet {
//0927 (5)
	
	//  http://localhost:8088/JSP/jstl2
	
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		// 회원정보 2개 더 생성(총3개) => ArrayList에 저장 => set2.jsp 에서 마지막에 넣은 회원정보만 출력
		ArrayList memberList = new ArrayList();
		
		
		for(int i=0;i<3;i++) {
			MemberBean mb2 = new MemberBean();
			mb2.setId("itwill"+i);
			mb2.setPw("1234"+i);
			mb2.setName("아이티윌1"+i);
			mb2.setAge(20+i);
			mb2.setGender("여"+i);
			mb2.setEmail("itwill"+i+"@itwill.com");
			
			memberList.add(mb2);
		
		}
		
		System.out.println(memberList);
		
		//request 영역에 저장 (보내기 위해서는)
		request.setAttribute("memberList", memberList);
		
		
		//forward 이동
		RequestDispatcher dis = 
				request.getRequestDispatcher("./jstl/core_foreach.jsp");// ./ (웹앱의미)
		dis.forward(request, response);
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
			//잘 안쓰이므로 안에 내용 지워놓고 일단 get만 사용하기.
	}

	
	
}
