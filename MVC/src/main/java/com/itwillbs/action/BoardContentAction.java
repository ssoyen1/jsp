 package com.itwillbs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.db.BoardDAO;
import com.itwillbs.db.BoardDTO;

public class BoardContentAction implements Action { // 글 적은 것 내용보기

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      System.out.println(" M : BoardContentAction_execute() 호출");
      // 디비에 연결하는 것을 처리하기위해 Model을 사용한다
      
      //(1) 전달정보(파라메터) 저장 (모델원투 상관없이)
      int bno = Integer.parseInt(request.getParameter("bno")); // 원래 string으로 받는 애들 int로바꿔줌
      String pageNum = request.getParameter("pageNum"); // BoardListAction
      
      System.out.println(" M : bno : " + bno + ", pageNum : "+ pageNum);
      
      // DAO 객체 생성 (DB를 써야겠다라고 생각하면 무조건 DAO객체를 생성해야한다 생각하기
      					// 글정보를 저장해야겠다 생각하면 무조건 DTO를 불러오는 것 처럼)
      BoardDAO dao = new BoardDAO();
      
      
      //(2) 글 조회수 1증가 -> DAO 1증가 메서드 호출
      dao.updateReadcount(bno);
      System.out.println(" M : 조회수 1증가 완료");
      
      
      //(3) 글 번호에 해당하는 글정보를 가져오기
      BoardDTO dto = dao.getBoard(bno); // bno를 줘서 bno에 해당하는 정보가있는지 체크한 뒤 그 정보를 모두 다 가져와라..
      					 // getBoard의 리턴값은 글 정보. 우리는 글 정보를 다 dto에 저장한다.
      
      
      //(4) request 영역에 글정보를 저장
      request.setAttribute("dto", dto);
      request.setAttribute("pageNum", pageNum);
      
      
      //(5) 페이지 이동(준비) - 빈티켓 준비
      ActionForward forward = new ActionForward();
      forward.setPath("./board/boardContent.jsp"); 
      forward.setRedirect(false); // 위의 request를 가져가므로 false(forward-포워딩을 해야만 리퀘스트영역을 전달할 수있는것!!!!!)
      
      
      
      return forward; // 이동하려면 티켓을 전달해야함 다시 컨트롤러로 가야함.
   }

}