package com.itwillbs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.db.BoardDAO;
import com.itwillbs.db.BoardDTO;

public class BoardWriteAction implements Action { //빈껍데기지만 업캐스팅해서 쓸수있게만들수 있따
   // * 이 클래스의 목적?
   // 글 쓰기 동작을 수행하는 객체 생성을 위해!
   // 기능 단위로 모듈을 나눈다고 생각하기
   
	
	
		  // 글쓰기 동작을 수행하는 객체
		   @Override
		   public ActionForward execute(HttpServletRequest request, 
				   HttpServletResponse response) throws Exception {
	
	      System.out.println(" M : BoardWriteAction_execute() 호출");
	      
	      
	      // 한글처리
//	      request.setCharacterEncoding("UTF-8"); // 귀찮아서 자동으로 체크할 수 있도록 만들기 필터이용해서
	      
	      
	      
	      // 전달정보 (파라미터 저장)
	      BoardDTO dto = new BoardDTO();
	      
	      dto.setSubject(request.getParameter("subject"));
	      dto.setName(request.getParameter("name"));
	      dto.setPass(request.getParameter("pass")); // writeForm.jsp에서 넘어옴. 인풋테그에서 넘어옴
	      dto.setContent(request.getParameter("content"));
	      
	      
	      // ip 저장
	      dto.setIp(request.getRemoteAddr());
	      
	      System.out.println(" M : "+dto);
	      
	      
	      // BoardDAO 객체 // db에 넣으러가보자고. db에대한 동작을 수행해야하니까 따로 생성해야함
	      BoardDAO dao = new BoardDAO();
	      
	      // insertBoard() 메서드 사용
	      dao.insertBoard(dto);
	      
	      
	      // 페이지 이동정보생성(티켓생성)
	      ActionForward forward = new ActionForward();
	      forward.setPath("./BoardList.bo"); // list로 가야함
	      forward.setRedirect(true); // 
	      
	      return forward ; // execute 메서드의 리턴 ActionForward
	      				   // 위에 만든것을 돌려줌
	      				   // 	BoardFrontController의	bwa.execute (request, response); 에서 보낸 것
	      
	      
	      
	      
   }
   
}